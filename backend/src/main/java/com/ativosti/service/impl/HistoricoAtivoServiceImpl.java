package com.ativosti.service.impl;

import com.ativosti.dto.HistoricoAtivoRequestDTO;
import com.ativosti.dto.HistoricoAtivoResponseDTO;
import com.ativosti.model.Ativo;
import com.ativosti.model.HistoricoAtivo;
import com.ativosti.repository.AtivoRepository;
import com.ativosti.repository.HistoricoAtivoRepository;
import com.ativosti.service.HistoricoAtivoService;
import com.ativosti.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoAtivoServiceImpl implements HistoricoAtivoService {

    @Autowired
    private HistoricoAtivoRepository historicoAtivoRepository;

    @Autowired
    private AtivoRepository ativoRepository;

    @Override
    public List<HistoricoAtivoResponseDTO> listarTodos() {
        return historicoAtivoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoricoAtivoResponseDTO buscarPorId(Long id) {
        HistoricoAtivo historico = historicoAtivoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Histórico de ativo", id));
        return toResponseDTO(historico);
    }

    @Override
    public List<HistoricoAtivoResponseDTO> buscarPorAtivo(Long ativoId) {
        if (!ativoRepository.existsById(ativoId)) {
            throw MessageUtils.notFound("Ativo", ativoId);
        }
        return historicoAtivoRepository.findByAtivoIdOrderByDataAlteracaoDesc(ativoId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HistoricoAtivoResponseDTO registrarAlteracao(HistoricoAtivoRequestDTO dto) {
        Ativo ativo = ativoRepository.findById(dto.getAtivoId())
                .orElseThrow(() -> MessageUtils.notFound("Ativo", dto.getAtivoId()));

        HistoricoAtivo historico = new HistoricoAtivo();
        historico.setAtivo(ativo);
        historico.setCampoAlterado(dto.getCampoAlterado());
        historico.setValorAntigo(dto.getValorAntigo());
        historico.setValorNovo(dto.getValorNovo());
        historico.setChamadoGlpi(dto.getChamadoGlpi());
        historico.setUsuarioId(1L); // temporário

        HistoricoAtivo salvo = historicoAtivoRepository.save(historico);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!historicoAtivoRepository.existsById(id)) {
            throw MessageUtils.notFound("Histórico de ativo", id);
        }
        historicoAtivoRepository.deleteById(id);
    }

    private HistoricoAtivoResponseDTO toResponseDTO(HistoricoAtivo historico) {
        return new HistoricoAtivoResponseDTO(
                historico.getId(),
                historico.getAtivo().getId(),
                historico.getAtivo().getPatrimonio(),
                historico.getCampoAlterado(),
                historico.getValorAntigo(),
                historico.getValorNovo(),
                historico.getChamadoGlpi(),
                historico.getDataAlteracao(),
                historico.getUsuarioId()
        );
    }
}