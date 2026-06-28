package com.ativosti.service.impl;

import com.ativosti.dto.AtivoRequestDTO;
import com.ativosti.dto.AtivoResponseDTO;
import com.ativosti.model.Ativo;
import com.ativosti.model.Localizacao;
import com.ativosti.model.OrdemCompra;
import com.ativosti.model.TipoAtivo;
import com.ativosti.repository.AtivoRepository;
import com.ativosti.repository.LocalizacaoRepository;
import com.ativosti.repository.OrdemCompraRepository;
import com.ativosti.repository.TipoAtivoRepository;
import com.ativosti.service.AtivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AtivoServiceImpl implements AtivoService {

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
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado com id: " + id));
        return toResponseDTO(ativo);
    }

    @Override
    @Transactional
    public AtivoResponseDTO criar(AtivoRequestDTO dto) {
        TipoAtivo tipo = tipoAtivoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de ativo não encontrado com id: " + dto.getTipoId()));
        Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada com id: " + dto.getLocalizacaoId()));

        OrdemCompra ordemCompra = null;
        if (dto.getOrdemCompraId() != null) {
            ordemCompra = ordemCompraRepository.findById(dto.getOrdemCompraId())
                    .orElseThrow(() -> new RuntimeException("Ordem de compra não encontrada com id: " + dto.getOrdemCompraId()));
        }

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
                .orElseThrow(() -> new RuntimeException("Ativo não encontrado com id: " + id));

        TipoAtivo tipo = tipoAtivoRepository.findById(dto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo de ativo não encontrado com id: " + dto.getTipoId()));
        Localizacao localizacao = localizacaoRepository.findById(dto.getLocalizacaoId())
                .orElseThrow(() -> new RuntimeException("Localização não encontrada com id: " + dto.getLocalizacaoId()));

        OrdemCompra ordemCompra = null;
        if (dto.getOrdemCompraId() != null) {
            ordemCompra = ordemCompraRepository.findById(dto.getOrdemCompraId())
                    .orElseThrow(() -> new RuntimeException("Ordem de compra não encontrada com id: " + dto.getOrdemCompraId()));
        }

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
            throw new RuntimeException("Ativo não encontrado com id: " + id);
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