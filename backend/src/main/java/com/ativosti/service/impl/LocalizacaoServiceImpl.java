package com.ativosti.service.impl;

import com.ativosti.dto.LocalizacaoRequestDTO;
import com.ativosti.dto.LocalizacaoResponseDTO;
import com.ativosti.model.Cidade;
import com.ativosti.model.Localizacao;
import com.ativosti.repository.CidadeRepository;
import com.ativosti.repository.LocalizacaoRepository;
import com.ativosti.service.LocalizacaoService;
import com.ativosti.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocalizacaoServiceImpl implements LocalizacaoService {

    @Autowired
    private LocalizacaoRepository localizacaoRepository;

    @Autowired
    private CidadeRepository cidadeRepository;

    @Override
    public List<LocalizacaoResponseDTO> listarTodas() {
        return localizacaoRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public LocalizacaoResponseDTO buscarPorId(Long id) {
        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Localização", id));
        return toResponseDTO(localizacao);
    }

    @Override
    public List<LocalizacaoResponseDTO> buscarPorCidade(Long cidadeId) {
        if (!cidadeRepository.existsById(cidadeId)) {
            throw MessageUtils.notFound("Cidade", cidadeId);
        }
        return localizacaoRepository.findByCidadeId(cidadeId)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public LocalizacaoResponseDTO criar(LocalizacaoRequestDTO dto) {
        Cidade cidade = cidadeRepository.findById(dto.getCidadeId())
                .orElseThrow(() -> MessageUtils.notFound("Cidade", dto.getCidadeId()));

        if (localizacaoRepository.findByCidadeIdAndTipoPontoAndNomePonto(
                dto.getCidadeId(), dto.getTipoPonto(), dto.getNomePonto()).isPresent()) {
            throw MessageUtils.alreadyExists("localização", "cidade e ponto",
                    dto.getTipoPonto() + " - " + dto.getNomePonto());
        }

        Localizacao localizacao = new Localizacao();
        localizacao.setCidade(cidade);
        localizacao.setTipoPonto(dto.getTipoPonto());
        localizacao.setNomePonto(dto.getNomePonto());

        Localizacao salvo = localizacaoRepository.save(localizacao);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public LocalizacaoResponseDTO atualizar(Long id, LocalizacaoRequestDTO dto) {
        Localizacao localizacao = localizacaoRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Localização", id));

        Cidade cidade = cidadeRepository.findById(dto.getCidadeId())
                .orElseThrow(() -> MessageUtils.notFound("Cidade", dto.getCidadeId()));

        if (!localizacao.getCidade().getId().equals(dto.getCidadeId()) ||
                !localizacao.getTipoPonto().equals(dto.getTipoPonto()) ||
                !localizacao.getNomePonto().equals(dto.getNomePonto())) {

            if (localizacaoRepository.findByCidadeIdAndTipoPontoAndNomePonto(
                    dto.getCidadeId(), dto.getTipoPonto(), dto.getNomePonto()).isPresent()) {
                throw MessageUtils.alreadyExists("localização", "cidade e ponto",
                        dto.getTipoPonto() + " - " + dto.getNomePonto());
            }
        }

        localizacao.setCidade(cidade);
        localizacao.setTipoPonto(dto.getTipoPonto());
        localizacao.setNomePonto(dto.getNomePonto());

        Localizacao atualizado = localizacaoRepository.save(localizacao);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!localizacaoRepository.existsById(id)) {
            throw MessageUtils.notFound("Localização", id);
        }
        localizacaoRepository.deleteById(id);
    }

    private LocalizacaoResponseDTO toResponseDTO(Localizacao loc) {
        return new LocalizacaoResponseDTO(
                loc.getId(),
                loc.getCidade().getId(),
                loc.getCidade().getNome(),
                loc.getTipoPonto(),
                loc.getNomePonto()
        );
    }
}