package com.ativosti.service;

import com.ativosti.dto.AtivoRequestDTO;
import com.ativosti.dto.AtivoResponseDTO;
import java.util.List;

public interface AtivoService {
    List<AtivoResponseDTO> listarTodos();
    AtivoResponseDTO buscarPorId(Long id);
    AtivoResponseDTO criar(AtivoRequestDTO dto);
    AtivoResponseDTO atualizar(Long id, AtivoRequestDTO dto);
    void deletar(Long id);
}