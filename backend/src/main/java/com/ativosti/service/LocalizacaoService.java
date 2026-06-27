package com.ativosti.service;

import com.ativosti.dto.LocalizacaoRequestDTO;
import com.ativosti.dto.LocalizacaoResponseDTO;
import java.util.List;

public interface LocalizacaoService {
    List<LocalizacaoResponseDTO> listarTodas();
    LocalizacaoResponseDTO buscarPorId(Long id);
    List<LocalizacaoResponseDTO> buscarPorCidade(Long cidadeId);
    LocalizacaoResponseDTO criar(LocalizacaoRequestDTO dto);
    LocalizacaoResponseDTO atualizar(Long id, LocalizacaoRequestDTO dto);
    void deletar(Long id);
}