package com.ativosti.service;

import com.ativosti.dto.PerfilRequestDTO;
import com.ativosti.dto.PerfilResponseDTO;
import java.util.List;

public interface PerfilService {
    List<PerfilResponseDTO> listarTodos();
    PerfilResponseDTO buscarPorId(Long id);
    PerfilResponseDTO criar(PerfilRequestDTO dto);
    PerfilResponseDTO atualizar(Long id, PerfilRequestDTO dto);
    void deletar(Long id);
}
