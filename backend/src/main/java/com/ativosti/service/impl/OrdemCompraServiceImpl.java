package com.ativosti.service.impl;

import com.ativosti.dto.OrdemCompraRequestDTO;
import com.ativosti.dto.OrdemCompraResponseDTO;
import com.ativosti.model.OrdemCompra;
import com.ativosti.repository.OrdemCompraRepository;
import com.ativosti.service.OrdemCompraService;
import com.ativosti.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrdemCompraServiceImpl implements OrdemCompraService {

    @Autowired
    private OrdemCompraRepository ordemCompraRepository;

    @Override
    public List<OrdemCompraResponseDTO> listarTodos() {
        return ordemCompraRepository.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrdemCompraResponseDTO buscarPorId(Long id) {
        OrdemCompra oc = ordemCompraRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Ordem de compra", id));
        return toResponseDTO(oc);
    }

    @Override
    @Transactional
    public OrdemCompraResponseDTO criar(OrdemCompraRequestDTO dto) {
        if (ordemCompraRepository.findByNumeroOc(dto.getNumeroOc()).isPresent()) {
            throw MessageUtils.alreadyExists("ordem de compra", "número OC", dto.getNumeroOc());
        }

        OrdemCompra oc = new OrdemCompra();
        oc.setNumeroOc(dto.getNumeroOc());
        oc.setDataCompra(dto.getDataCompra());
        oc.setFornecedor(dto.getFornecedor());
        oc.setArquivoPath(dto.getArquivoPath());

        OrdemCompra salvo = ordemCompraRepository.save(oc);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public OrdemCompraResponseDTO atualizar(Long id, OrdemCompraRequestDTO dto) {
        OrdemCompra oc = ordemCompraRepository.findById(id)
                .orElseThrow(() -> MessageUtils.notFound("Ordem de compra", id));

        if (!oc.getNumeroOc().equals(dto.getNumeroOc()) &&
                ordemCompraRepository.findByNumeroOc(dto.getNumeroOc()).isPresent()) {
            throw MessageUtils.alreadyExists("ordem de compra", "número OC", dto.getNumeroOc());
        }

        oc.setNumeroOc(dto.getNumeroOc());
        oc.setDataCompra(dto.getDataCompra());
        oc.setFornecedor(dto.getFornecedor());
        oc.setArquivoPath(dto.getArquivoPath());

        OrdemCompra atualizado = ordemCompraRepository.save(oc);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!ordemCompraRepository.existsById(id)) {
            throw MessageUtils.notFound("Ordem de compra", id);
        }
        ordemCompraRepository.deleteById(id);
    }

    private OrdemCompraResponseDTO toResponseDTO(OrdemCompra oc) {
        return new OrdemCompraResponseDTO(
                oc.getId(),
                oc.getNumeroOc(),
                oc.getDataCompra(),
                oc.getFornecedor(),
                oc.getArquivoPath()
        );
    }
}