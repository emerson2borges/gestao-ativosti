package com.ativosti.service.impl;

import com.ativosti.dto.TipoAtivoRequestDTO;
import com.ativosti.dto.TipoAtivoResponseDTO;
import com.ativosti.model.TipoAtivo;
import com.ativosti.repository.TipoAtivoRepository;
import com.ativosti.service.TipoAtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TipoAtivoServiceImpl implements TipoAtivoService {

    @Autowired
    private TipoAtivoRepository tipoAtivoRepository;

    @Override
    public List<TipoAtivoResponseDTO> listarTodos() {
        return tipoAtivoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TipoAtivoResponseDTO buscarPorId(Long id) {
        TipoAtivo tipo = tipoAtivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de ativo não encontrado com id: " + id));
        return toResponseDTO(tipo);
    }

    @Override
    @Transactional
    public TipoAtivoResponseDTO criar(TipoAtivoRequestDTO dto) {
        if (tipoAtivoRepository.findByNome(dto.getNome()).isPresent()) {
            throw new RuntimeException("Já existe um tipo de ativo com o nome: " + dto.getNome());
        }
        TipoAtivo tipo = new TipoAtivo();
        tipo.setNome(dto.getNome());
        TipoAtivo salvo = tipoAtivoRepository.save(tipo);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public TipoAtivoResponseDTO atualizar(Long id, TipoAtivoRequestDTO dto) {
        TipoAtivo tipo = tipoAtivoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo de ativo não encontrado com id: " + id));
        tipo.setNome(dto.getNome());
        TipoAtivo atualizado = tipoAtivoRepository.save(tipo);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!tipoAtivoRepository.existsById(id)) {
            throw new RuntimeException("Tipo de ativo não encontrado com id: " + id);
        }
        tipoAtivoRepository.deleteById(id);
    }

    private TipoAtivoResponseDTO toResponseDTO(TipoAtivo tipo) {
        return new TipoAtivoResponseDTO(tipo.getId(), tipo.getNome());
    }
}