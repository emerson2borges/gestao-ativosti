package com.ativosti.service.impl;

import com.ativosti.dto.CidadeRequestDTO;
import com.ativosti.dto.CidadeResponseDTO;
import com.ativosti.model.Cidade;
import com.ativosti.repository.CidadeRepository;
import com.ativosti.service.CidadeService;
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
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com id: " + id));
        return toResponseDTO(cidade);
    }

    @Override
    @Transactional
    public CidadeResponseDTO criar(CidadeRequestDTO dto) {
        Cidade cidade = new Cidade();
        cidade.setNome(dto.getNome());
        Cidade saved = cidadeRepository.save(cidade);
        return toResponseDTO(saved);
    }

    @Override
    @Transactional
    public CidadeResponseDTO atualizar(Long id, CidadeRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com id: " + id));
        cidade.setNome(dto.getNome());
        Cidade updated = cidadeRepository.save(cidade);
        return toResponseDTO(updated);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!cidadeRepository.existsById(id)) {
            throw new RuntimeException("Cidade não encontrada com id: " + id);
        }
        cidadeRepository.deleteById(id);
    }

    // Método auxiliar para converter entidade → DTO
    private CidadeResponseDTO toResponseDTO(Cidade cidade) {
        return new CidadeResponseDTO(cidade.getId(), cidade.getNome());
    }
}