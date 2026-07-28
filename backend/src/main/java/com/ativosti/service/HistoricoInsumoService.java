package com.ativosti.service;

import com.ativosti.dto.HistoricoInsumoRequestDTO;
import com.ativosti.dto.HistoricoInsumoResponseDTO;
import java.util.List;

public interface HistoricoInsumoService {
    List<HistoricoInsumoResponseDTO> listarTodos();
    HistoricoInsumoResponseDTO buscarPorId(Long id);
    List<HistoricoInsumoResponseDTO> buscarPorInsumo(Long insumoId);
    HistoricoInsumoResponseDTO registrarMovimentacao(HistoricoInsumoRequestDTO dto);
    void deletar(Long id);
}