package com.ativosti.service;

import com.ativosti.dto.AtivoRequestDTO;
import com.ativosti.dto.AtivoResponseDTO;
import com.ativosti.dto.InstalacaoSubativoResponseDTO;
import java.util.List;

public interface AtivoService {
    List<AtivoResponseDTO> listarTodos();
    AtivoResponseDTO buscarPorId(Long id);
    AtivoResponseDTO criar(AtivoRequestDTO dto);
    AtivoResponseDTO atualizar(Long id, AtivoRequestDTO dto);
    void deletar(Long id);

    InstalacaoSubativoResponseDTO instalarSubativo(Long ativoId, Long subativoId, String chamadoGlpi);

    InstalacaoSubativoResponseDTO removerSubativo(Long ativoId, Long subativoId, String chamadoGlpi);
}