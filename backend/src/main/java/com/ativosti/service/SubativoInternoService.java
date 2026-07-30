package com.ativosti.service;

import com.ativosti.dto.SubativoInternoRequestDTO;
import com.ativosti.dto.SubativoInternoResponseDTO;
import java.util.List;

public interface SubativoInternoService {
    List<SubativoInternoResponseDTO> listarTodos();
    SubativoInternoResponseDTO buscarPorId(Long id);
    List<SubativoInternoResponseDTO> buscarPorAtivo(Long ativoId);
    SubativoInternoResponseDTO criar(SubativoInternoRequestDTO dto);
    SubativoInternoResponseDTO atualizar(Long id, SubativoInternoRequestDTO dto);
    void deletar(Long id);
}