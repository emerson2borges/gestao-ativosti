package com.ativosti.service.impl;

import com.ativosti.dto.AtivoRequestDTO;
import com.ativosti.dto.AtivoResponseDTO;
import com.ativosti.exception.ResourceNotFoundException;
import com.ativosti.exception.BusinessException;
import com.ativosti.exception.ValidationException;
import com.ativosti.model.Ativo;
import com.ativosti.model.Localizacao;
import com.ativosti.model.OrdemCompra;
import com.ativosti.model.TipoAtivo;
import com.ativosti.repository.AtivoRepository;
import com.ativosti.repository.LocalizacaoRepository;
import com.ativosti.repository.OrdemCompraRepository;
import com.ativosti.repository.TipoAtivoRepository;
import com.ativosti.service.AtivoService;
import com.ativosti.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtivoServiceImpl implements AtivoService {

    private static final List<String> STATUS_VALIDOS = Arrays.asList("Em Uso", "Manutenção", "Estoque Sede", "Descartado");

    @Autowired
    private AtivoRepository ativoRepository;

    @Autowired
    private TipoAtivoRepository tipoAtivoRepository;

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private OrdemCompraRepository ordemCompraRepository;

    @Override
    public List<AtivoResponseDTO> listarTodos() {
        return ativoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public AtivoResponseDTO buscarPorId(Long id) {
        Ativo ativo = ativoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Ativo", id));
        return toResponseDTO(ativo);
    }

    private void validarStatus(String status) {
        if (!STATUS_VALIDOS.contains(status)) {
            throw MessageUtils.invalidStatus(status, STATUS_VALIDOS);
        }
    }

    @Override
    @Transactional
    public AtivoResponseDTO criar(AtivoRequestDTO dto) {
        TipoAtivo tipo = tipoAtivoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> MessageUtils.notFound("Tipo de ativo", dto.getTipoId()));
        Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                .orElseThrow(() -> MessageUtils.notFound("Localização", dto.getLocalizacaoId()));

        OrdemCompra ordemCompra = null;
        if (dto.getOrdemCompraId() != null) {
            ordemCompra = ordemCompraRepository.findById(dto.getOrdemCompraId())
                    .orElseThrow(() -> MessageUtils.notFound("Ordem de compra", dto.getOrdemCompraId()));
        }

        if (ativoRepository.findByPatrimonio(dto.getPatrimonio()).isPresent()) {
            throw MessageUtils.alreadyExists("ativo", "patrimônio", dto.getPatrimonio());
        }

        validarStatus(dto.getStatus());

        Ativo ativo = new Ativo();
        ativo.setTipo(tipo);
        ativo.setLocalizacao(localizacao);
        ativo.setOrdemCompra(ordemCompra);
        ativo.setPatrimonio(dto.getPatrimonio());
        ativo.setHostnameAtual(dto.getHostnameAtual());
        ativo.setResponsavel(dto.getResponsavel());
        ativo.setStatus(dto.getStatus());

        Ativo salvo = ativoRepository.save(ativo);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public AtivoResponseDTO atualizar(Long id, AtivoRequestDTO dto) {
        Ativo ativo = ativoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Ativo", id));

        TipoAtivo tipo = tipoAtivoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> MessageUtils.notFound("Tipo de ativo", dto.getTipoId()));
        Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                .orElseThrow(() -> MessageUtils.notFound("Localização", dto.getLocalizacaoId()));

        OrdemCompra ordemCompra = null;
        if (dto.getOrdemCompraId() != null) {
            ordemCompra = ordemCompraRepository.findById(dto.getOrdemCompraId())
                    .orElseThrow(() -> MessageUtils.notFound("Ordem de compra", dto.getOrdemCompraId()));
        }

        if (!ativo.getPatrimonio().equals(dto.getPatrimonio()) &&
            ativoRepository.findByPatrimonio(dto.getPatrimonio()).isPresent()) {
            throw MessageUtils.alreadyExists("ativo", "patrimônio", dto.getPatrimonio());
        }

        validarStatus(dto.getStatus());

        ativo.setTipo(tipo);
        ativo.setLocalizacao(localizacao);
        ativo.setOrdemCompra(ordemCompra);
        ativo.setPatrimonio(dto.getPatrimonio());
        ativo.setHostnameAtual(dto.getHostnameAtual());
        ativo.setResponsavel(dto.getResponsavel());
        ativo.setStatus(dto.getStatus());

        Ativo atualizado = ativoRepository.save(ativo);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!ativoRepository.existsById(id)) {
            throw MessageUtils.notFound("Ativo", id);
        }
        ativoRepository.deleteById(id);
    }

    private AtivoResponseDTO toResponseDTO(Ativo ativo) {
        AtivoResponseDTO dto = new AtivoResponseDTO();
        dto.setId(ativo.getId());
        dto.setPatrimonio(ativo.getPatrimonio());
        dto.setHostnameAtual(ativo.getHostnameAtual());
        dto.setResponsavel(ativo.getResponsavel());
        dto.setStatus(ativo.getStatus());

        dto.setTipoId(ativo.getTipo().getId());
        dto.setTipoNome(ativo.getTipo().getNome());

        dto.setLocalizacaoId(ativo.getLocalizacao().getId());
        dto.setLocalizacaoNome(ativo.getLocalizacao().getNomePonto());

        if (ativo.getOrdemCompra() != null) {
            dto.setOrdemCompraId(ativo.getOrdemCompra().getId());
            dto.setOrdemCompraNumero(ativo.getOrdemCompra().getNumeroOc());
        }

        return dto;
    }
}