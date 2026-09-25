package com.ativosti.service.impl;

import com.ativosti.dto.HistoricoInsumoRequestDTO;
import com.ativosti.dto.HistoricoInsumoResponseDTO;
import com.ativosti.model.EstoqueInsumo;
import com.ativosti.model.HistoricoInsumo;
import com.ativosti.model.Localizacao;
import com.ativosti.repository.EstoqueInsumoRepository;
import com.ativosti.repository.HistoricoInsumoRepository;
import com.ativosti.repository.LocalizacaoRepository;
import com.ativosti.service.HistoricoInsumoService;
import com.ativosti.utils.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HistoricoInsumoServiceImpl implements HistoricoInsumoService {

    @Autowired
    private HistoricoInsumoRepository historicoInsumoRepository;

    @Autowired
    private EstoqueInsumoRepository estoqueInsumoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    // UsuarioRepository removido temporariamente

    @Override
    public List<HistoricoInsumoResponseDTO> listarTodos() {
        return historicoInsumoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public HistoricoInsumoResponseDTO buscarPorId(Long id) {
        HistoricoInsumo historico = historicoInsumoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Histórico de insumo", id));
        return toResponseDTO(historico);
    }

    @Override
    public List<HistoricoInsumoResponseDTO> buscarPorInsumo(Long insumoId) {
        if (!estoqueInsumoRepository.existsById(insumoId)) {
            throw MessageUtils.notFound("Insumo", insumoId);
        }
        return historicoInsumoRepository.findByInsumoIdOrderByDataMovimentacaoDesc(insumoId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public HistoricoInsumoResponseDTO registrarMovimentacao(HistoricoInsumoRequestDTO dto) {
        EstoqueInsumo insumo = estoqueInsumoRepository.findById(dto.getInsumoId())
                .orElseThrow(() -> MessageUtils.notFound("Insumo", dto.getInsumoId()));

        if (!"ENTRADA".equals(dto.getTipoMovimentacao()) && !"SAIDA".equals(dto.getTipoMovimentacao())) {
            throw new IllegalArgumentException("Tipo de movimentação inválido. Use 'ENTRADA' ou 'SAIDA'.");
        }

        if ("ENTRADA".equals(dto.getTipoMovimentacao())) {
            insumo.setQuantidadeTotal(insumo.getQuantidadeTotal() + dto.getQuantidade());
            insumo.setQuantidadeDisponivel(insumo.getQuantidadeDisponivel() + dto.getQuantidade());
        } else {
            if (insumo.getQuantidadeDisponivel() < dto.getQuantidade()) {
                throw MessageUtils.invalidStock("quantidade disponível",
                        "insuficiente para a saída. Disponível: " + insumo.getQuantidadeDisponivel());
            }
            insumo.setQuantidadeDisponivel(insumo.getQuantidadeDisponivel() - dto.getQuantidade());
        }

        estoqueInsumoRepository.save(insumo);

        HistoricoInsumo historico = new HistoricoInsumo();
        historico.setInsumo(insumo);
        historico.setTipoMovimentacao(dto.getTipoMovimentacao());
        historico.setQuantidade(dto.getQuantidade());
        historico.setChamadoGlpi(dto.getChamadoGlpi());

        if (dto.getLocalizacaoId() != null) {
            Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                    .orElseThrow(() -> MessageUtils.notFound("Localização", dto.getLocalizacaoId()));
            historico.setLocalizacao(localizacao);
        }

        // Temporário: usuário fixo
        historico.setUsuarioId(1L);

        HistoricoInsumo salvo = historicoInsumoRepository.save(historico);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!historicoInsumoRepository.existsById(id)) {
            throw MessageUtils.notFound("Histórico de insumo", id);
        }
        historicoInsumoRepository.deleteById(id);
    }

    private HistoricoInsumoResponseDTO toResponseDTO(HistoricoInsumo historico) {
        return new HistoricoInsumoResponseDTO(
                historico.getId(),
                historico.getInsumo().getId(),
                historico.getInsumo().getNomeItem(),
                historico.getTipoMovimentacao(),
                historico.getLocalizacao() != null ? historico.getLocalizacao().getId() : null,
                historico.getLocalizacao() != null ? historico.getLocalizacao().getNomePonto() : null,
                historico.getQuantidade(),
                historico.getChamadoGlpi(),
                historico.getDataMovimentacao(),
                1L, // usuário fixo
                "Admin" // nome fixo
        );
    }
}