package com.ativosti.service;

import com.ativosti.dto.EstoqueInsumoRequestDTO;
import com.ativosti.dto.EstoqueInsumoResponseDTO;
import java.util.List;

public interface EstoqueInsumoService {
    List<EstoqueInsumoResponseDTO> listarTodos();
    EstoqueInsumoResponseDTO buscarPorId(Long id);
    List<EstoqueInsumoResponseDTO> buscarPorOrdemCompra(Long ordemCompraId);
    EstoqueInsumoResponseDTO criar(EstoqueInsumoRequestDTO dto);
    EstoqueInsumoResponseDTO atualizar(Long id, EstoqueInsumoRequestDTO dto);
    void deletar(Long id);
}