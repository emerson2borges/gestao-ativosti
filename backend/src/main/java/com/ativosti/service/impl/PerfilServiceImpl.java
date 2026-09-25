package com.ativosti.service.impl;

import com.ativosti.dto.PerfilRequestDTO;
import com.ativosti.dto.PerfilResponseDTO;
import com.ativosti.dto.PermissaoResponseDTO;
import com.ativosti.model.Perfil;
import com.ativosti.model.Permissao;
import com.ativosti.repository.PerfilRepository;
import com.ativosti.repository.PermissaoRepository;
import com.ativosti.service.PerfilService;
import com.ativosti.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PerfilServiceImpl implements PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public List<PerfilResponseDTO> listarTodos() {
        return perfilRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PerfilResponseDTO buscarPorId(Long id) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Perfil", id));
        return toResponseDTO(perfil);
    }

    @Override
    @Transactional
    public PerfilResponseDTO criar(PerfilRequestDTO dto) {
        if (perfilRepository.findByNome(dto.getNome()).isPresent()) {
            throw MessageUtils.alreadyExists("Perfil", "nome", dto.getNome());
        }

        Perfil perfil = new Perfil();
        perfil.setNome(dto.getNome());

        if (dto.getPermissaoIds() != null && !dto.getPermissaoIds().isEmpty()) {
            List<Permissao> permissoes = permissaoRepository.findAllById(dto.getPermissaoIds());
            perfil.setPermissoes(new HashSet<>(permissoes));
        }

        Perfil criado = perfilRepository.save(perfil);
        return toResponseDTO(criado);
    }

    @Override
    @Transactional
    public PerfilResponseDTO atualizar(Long id, PerfilRequestDTO dto) {
        Perfil perfil = perfilRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Perfil", id));

        if (!perfil.getNome().equals(dto.getNome()) &&
            perfilRepository.findByNome(dto.getNome()).isPresent()) {
            throw MessageUtils.alreadyExists("Perfil", "nome", dto.getNome());
        }

        perfil.setNome(dto.getNome());

        if (dto.getPermissaoIds() != null) {
            List<Permissao> permissoes = permissaoRepository.findAllById(dto.getPermissaoIds());
            perfil.setPermissoes(new HashSet<>(permissoes));
        }

        Perfil atualizado = perfilRepository.save(perfil);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!perfilRepository.existsById(id)) {
            throw MessageUtils.notFound("Perfil", id);
        }
        perfilRepository.deleteById(id);
    }

    private PerfilResponseDTO toResponseDTO(Perfil p) {
        Set<PermissaoResponseDTO> permissoesDTO = p.getPermissoes().stream()
                .map(perm -> new PermissaoResponseDTO(perm.getId(), perm.getChave(), perm.getDescricao()))
                .collect(Collectors.toSet());
        return new PerfilResponseDTO(p.getId(), p.getNome(), permissoesDTO);
    }
}
