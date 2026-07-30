package com.ativosti.service;

import com.ativosti.dto.HistoricoAtivoRequestDTO;
import com.ativosti.dto.HistoricoAtivoResponseDTO;
import java.util.List;

public interface HistoricoAtivoService {
    List<HistoricoAtivoResponseDTO> listarTodos();
    HistoricoAtivoResponseDTO buscarPorId(Long id);
    List<HistoricoAtivoResponseDTO> buscarPorAtivo(Long ativoId);
    HistoricoAtivoResponseDTO registrarAlteracao(HistoricoAtivoRequestDTO dto);
    void deletar(Long id);
}