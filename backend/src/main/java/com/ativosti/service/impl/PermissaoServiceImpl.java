package com.ativosti.service.impl;

import com.ativosti.dto.PermissaoResponseDTO;
import com.ativosti.model.Permissao;
import com.ativosti.repository.PermissaoRepository;
import com.ativosti.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PermissaoServiceImpl implements PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    @Override
    public List<PermissaoResponseDTO> listarTodas() {
        return permissaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    private PermissaoResponseDTO toResponseDTO(Permissao p) {
        return new PermissaoResponseDTO(p.getId(), p.getChave(), p.getDescricao());
    }
}
