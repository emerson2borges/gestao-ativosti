package com.ativosti.service;

import com.ativosti.dto.OrdemCompraRequestDTO;
import com.ativosti.dto.OrdemCompraResponseDTO;
import java.util.List;

public interface OrdemCompraService {
    List<OrdemCompraResponseDTO> listarTodos();
    OrdemCompraResponseDTO buscarPorId(Long id);
    OrdemCompraResponseDTO criar(OrdemCompraRequestDTO dto);
    OrdemCompraResponseDTO atualizar(Long id, OrdemCompraRequestDTO dto);
    void deletar(Long id);
}