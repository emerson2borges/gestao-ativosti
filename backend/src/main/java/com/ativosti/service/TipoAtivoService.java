package com.ativosti.service;

import com.ativosti.dto.TipoAtivoRequestDTO;
import com.ativosti.dto.TipoAtivoResponseDTO;
import java.util.List;

public interface TipoAtivoService {
    List<TipoAtivoResponseDTO> listarTodos();
    TipoAtivoResponseDTO buscarPorId(Long id);
    TipoAtivoResponseDTO criar(TipoAtivoRequestDTO dto);
    TipoAtivoResponseDTO atualizar(Long id, TipoAtivoRequestDTO dto);
    void deletar(Long id);
}