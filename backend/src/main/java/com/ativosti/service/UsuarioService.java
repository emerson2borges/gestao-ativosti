package com.ativosti.service;

import com.ativosti.dto.UsuarioRequestDTO;
import com.ativosti.dto.UsuarioResponseDTO;
import java.util.List;

public interface UsuarioService {
    List<UsuarioResponseDTO> listarTodos();
    UsuarioResponseDTO buscarPorId(Long id);
    UsuarioResponseDTO criar(UsuarioRequestDTO dto);
    UsuarioResponseDTO atualizar(Long id, UsuarioRequestDTO dto);
    void deletar(Long id);
}
