package com.ativosti.service.impl;

import com.ativosti.dto.SubativoInternoRequestDTO;
import com.ativosti.dto.SubativoInternoResponseDTO;
import com.ativosti.model.Ativo;
import com.ativosti.model.SubativoInterno;
import com.ativosti.repository.AtivoRepository;
import com.ativosti.repository.SubativoInternoRepository;
import com.ativosti.service.SubativoInternoService;
import com.ativosti.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubativoInternoServiceImpl implements SubativoInternoService {

    @Autowired
    private SubativoInternoRepository subativoInternoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Override
    public List<SubativoInternoResponseDTO> listarTodos() {
        return subativoInternoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SubativoInternoResponseDTO buscarPorId(Long id) {
        SubativoInterno subativo = subativoInternoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Subativo interno", id));
        return toResponseDTO(subativo);
    }

    @Override
    public List<SubativoInternoResponseDTO> buscarPorAtivo(Long ativoId) {
        // Verifica se o ativo existe (opcional, mas boa prática)
        if (!ativoRepository.existsById(ativoId)) {
            throw MessageUtils.notFound("Ativo", ativoId);
        }
        return subativoInternoRepository.findByAtivoId(ativoId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public SubativoInternoResponseDTO criar(SubativoInternoRequestDTO dto) {
        Ativo ativo = ativoRepository.findById(dto.getAtivoId())
                .orElseThrow(() -> MessageUtils.notFound("Ativo", dto.getAtivoId()));

        SubativoInterno subativo = new SubativoInterno();
        subativo.setAtivo(ativo);
        subativo.setTipoComponente(dto.getTipoComponente());
        subativo.setEspecificacao(dto.getEspecificacao());
        subativo.setQuantidade(dto.getQuantidade());
        subativo.setChamadoGlpi(dto.getChamadoGlpi());

        SubativoInterno salvo = subativoInternoRepository.save(subativo);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public SubativoInternoResponseDTO atualizar(Long id, SubativoInternoRequestDTO dto) {
        SubativoInterno subativo = subativoInternoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Subativo interno", id));

        Ativo ativo = ativoRepository.findById(dto.getAtivoId())
                .orElseThrow(() -> MessageUtils.notFound("Ativo", dto.getAtivoId()));

        subativo.setAtivo(ativo);
        subativo.setTipoComponente(dto.getTipoComponente());
        subativo.setEspecificacao(dto.getEspecificacao());
        subativo.setQuantidade(dto.getQuantidade());
        subativo.setChamadoGlpi(dto.getChamadoGlpi());

        SubativoInterno atualizado = subativoInternoRepository.save(subativo);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!subativoInternoRepository.existsById(id)) {
            throw MessageUtils.notFound("Subativo interno", id);
        }
        subativoInternoRepository.deleteById(id);
    }

    private SubativoInternoResponseDTO toResponseDTO(SubativoInterno subativo) {
        return new SubativoInternoResponseDTO(
                subativo.getId(),
                subativo.getAtivo().getId(),
                subativo.getAtivo().getPatrimonio(),
                subativo.getTipoComponente(),
                subativo.getEspecificacao(),
                subativo.getQuantidade(),
                subativo.getChamadoGlpi()
        );
    }
}