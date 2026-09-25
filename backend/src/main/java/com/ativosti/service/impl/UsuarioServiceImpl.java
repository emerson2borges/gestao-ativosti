package com.ativosti.service.impl;

import com.ativosti.dto.UsuarioRequestDTO;
import com.ativosti.dto.UsuarioResponseDTO;
import com.ativosti.model.Perfil;
import com.ativosti.model.Usuario;
import com.ativosti.repository.PerfilRepository;
import com.ativosti.repository.UsuarioRepository;
import com.ativosti.service.UsuarioService;
import com.ativosti.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PerfilRepository perfilRepository;

    @Override
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Usuario", id));
        return toResponseDTO(u);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO criar(UsuarioRequestDTO dto) {
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw MessageUtils.alreadyExists("Usuario", "email", dto.getEmail());
        }
        if (usuarioRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw MessageUtils.alreadyExists("Usuario", "matricula", dto.getMatricula());
        }

        Perfil perfil = perfilRepository.findById(dto.getPerfilId())
                .orElseThrow(() -> MessageUtils.notFound("Perfil", dto.getPerfilId()));

        Usuario u = new Usuario();
        u.setNome(dto.getNome());
        u.setMatricula(dto.getMatricula());
        u.setEmail(dto.getEmail());
        u.setSenha(dto.getSenha() != null ? dto.getSenha() : "123456");
        u.setPerfil(perfil);

        Usuario criado = usuarioRepository.save(u);
        return toResponseDTO(criado);
    }

    @Override
    @Transactional
    public UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto) {
        Usuario u = usuarioRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Usuario", id));

        if (!u.getEmail().equals(dto.getEmail()) &&
            usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw MessageUtils.alreadyExists("Usuario", "email", dto.getEmail());
        }

        if (!u.getMatricula().equals(dto.getMatricula()) &&
            usuarioRepository.findByMatricula(dto.getMatricula()).isPresent()) {
            throw MessageUtils.alreadyExists("Usuario", "matricula", dto.getMatricula());
        }

        Perfil perfil = perfilRepository.findById(dto.getPerfilId())
                .orElseThrow(() -> MessageUtils.notFound("Perfil", dto.getPerfilId()));

        u.setNome(dto.getNome());
        u.setMatricula(dto.getMatricula());
        u.setEmail(dto.getEmail());
        if (dto.getSenha() != null && !dto.getSenha().isBlank()) {
            u.setSenha(dto.getSenha());
        }
        u.setPerfil(perfil);

        Usuario atualizado = usuarioRepository.save(u);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw MessageUtils.notFound("Usuario", id);
        }
        usuarioRepository.deleteById(id);
    }

    private UsuarioResponseDTO toResponseDTO(Usuario u) {
        return new UsuarioResponseDTO(
                u.getId(),
                u.getNome(),
                u.getMatricula(),
                u.getEmail(),
                u.getPerfil() != null ? u.getPerfil().getId() : null,
                u.getPerfil() != null ? u.getPerfil().getNome() : null
        );
    }
}
