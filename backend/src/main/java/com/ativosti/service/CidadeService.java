
package com.ativosti.service;

import com.ativosti.dto.CidadeRequestDTO;
import com.ativosti.dto.CidadeResponseDTO;
import java.util.List;

public interface CidadeService {
    List<CidadeResponseDTO> listarTodas();
    CidadeResponseDTO buscarPorId(Long id);
    CidadeResponseDTO criar(CidadeRequestDTO dto);
    CidadeResponseDTO atualizar(Long id, CidadeRequestDTO dto);
    void deletar(Long id);
}