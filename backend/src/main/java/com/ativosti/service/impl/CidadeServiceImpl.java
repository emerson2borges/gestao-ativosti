package com.ativosti.service.impl;

import com.ativosti.dto.CidadeRequestDTO;
import com.ativosti.dto.CidadeResponseDTO;
import com.ativosti.model.Cidade;
import com.ativosti.util.MessageUtils;
import com.ativosti.service.CidadeService;
import com.ativosti.repository.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeServiceImpl implements CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public List<CidadeResponseDTO> listarTodas() {
        return cidadeRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CidadeResponseDTO buscarPorId(Long id) {
        Cidade cidade = cidadeRepository.findById(id)
            .orElseThrow(() -> MessageUtils.notFound("Cidade", id));
        return toResponseDTO(cidade);
    }

    @Override
    @Transactional
    public CidadeResponseDTO criar(CidadeRequestDTO dto) {
        if (cidadeRepository.findByNome(dto.getNome()).isPresent()) {
            throw MessageUtils.alreadyExists("cidade", "nome", dto.getNome());
        }

        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        Cidade criada = cidadeRepository.save(cidade);
        return toResponseDTO(criada);
    }

    @Override
    @Transactional
    public CidadeResponseDTO atualizar(Long id, CidadeRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(id)
            .orElseThrow(() -> MessageUtils.notFound("Cidade", id));
        
        if (!cidade.getNome().equals(dto.getNome()) &&
            cidadeRepository.findByNome(dto.getNome()).isPresent()) {
                throw MessageUtils.alreadyExists("cidade", "nome", dto.getNome());
        }
        cidade.setNome(dto.getNome());
        Cidade atualizada = cidadeRepository.save(cidade);
        return toResponseDTO(atualizada);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!cidadeRepository.existsById(id)) {
            throw MessageUtils.notFound("Cidade", id);
        }
        cidadeRepository.deleteById(id);
    }

    private CidadeResponseDTO toResponseDTO(Cidade cidade) {
        return new CidadeResponseDTO(cidade.getId(), cidade.getNome());
    }
}