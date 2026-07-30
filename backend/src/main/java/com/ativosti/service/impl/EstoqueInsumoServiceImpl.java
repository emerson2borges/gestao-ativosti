package com.ativosti.service.impl;

import com.ativosti.dto.EstoqueInsumoRequestDTO;
import com.ativosti.dto.EstoqueInsumoResponseDTO;
import com.ativosti.model.EstoqueInsumo;
import com.ativosti.model.OrdemCompra;
import com.ativosti.repository.EstoqueInsumoRepository;
import com.ativosti.repository.OrdemCompraRepository;
import com.ativosti.service.EstoqueInsumoService;
import com.ativosti.util.MessageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EstoqueInsumoServiceImpl implements EstoqueInsumoService {

    @Autowired
    private EstoqueInsumoRepository estoqueInsumoRepository;

    @Autowired
    private OrdemCompraRepository ordemCompraRepository;

    @Override
    public List<EstoqueInsumoResponseDTO> listarTodos() {
        return estoqueInsumoRepository.findAll()
            .stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    public EstoqueInsumoResponseDTO buscarPorId(Long id) {
        EstoqueInsumo insumo = estoqueInsumoRepository.findById(id)
            .orElseThrow(() -> MessageUtils.notFound("Estoque de insumo", id));
        return toResponseDTO(insumo);
    }

    @Override
    public List<EstoqueInsumoResponseDTO> buscarPorOrdemCompra(Long ordemCompraId) {
        if (!ordemCompraRepository.existsById(ordemCompraId)) {
            throw MessageUtils.notFound("Ordem de compra", ordemCompraId);
        }
        return estoqueInsumoRepository.findByOrdemCompraId(ordemCompraId)
            .stream()
            .map(this::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public EstoqueInsumoResponseDTO criar(EstoqueInsumoRequestDTO dto) {
        // Valida regra de negócio: disponível não pode ser maior que total
        if (dto.getQuantidadeDisponivel() > dto.getQuantidadeTotal()) {
            throw MessageUtils.invalidStock("quantidade disponível", "não pode ser maior que quantidade total");
        }

        // Verifica duplicidade de nome
        if (estoqueInsumoRepository.findByNomeItem(dto.getNomeItem()).isPresent()) {
            throw MessageUtils.alreadyExists("insumo", "nome", dto.getNomeItem());
        }

        OrdemCompra ordemCompra = null;
        if (dto.getOrdemCompraId() != null) {
            ordemCompra = ordemCompraRepository.findById(dto.getOrdemCompraId())
                .orElseThrow(() -> MessageUtils.notFound("Ordem de compra", dto.getOrdemCompraId()));
        }

        EstoqueInsumo insumo = new EstoqueInsumo();
        insumo.setOrdemCompra(ordemCompra);
        insumo.setNomeItem(dto.getNomeItem());
        insumo.setQuantidadeTotal(dto.getQuantidadeTotal());
        insumo.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());

        EstoqueInsumo salvo = estoqueInsumoRepository.save(insumo);
        return toResponseDTO(salvo);
    }

    @Override
    @Transactional
    public EstoqueInsumoResponseDTO atualizar(Long id, EstoqueInsumoRequestDTO dto) {
        EstoqueInsumo insumo = estoqueInsumoRepository.findById(id)
            .orElseThrow(() -> MessageUtils.notFound("Estoque de insumo", id));

        // Valida regra de negócio
        if (dto.getQuantidadeDisponivel() > dto.getQuantidadeTotal()) {
            throw MessageUtils.invalidStock("quantidade disponível", "não pode ser maior que quantidade total");
        }

        // Verifica duplicidade de nome se alterado
        if (!insumo.getNomeItem().equals(dto.getNomeItem()) &&
                estoqueInsumoRepository.findByNomeItem(dto.getNomeItem()).isPresent()) {
            throw MessageUtils.alreadyExists("insumo", "nome", dto.getNomeItem());
        }

        OrdemCompra ordemCompra = null;
        if (dto.getOrdemCompraId() != null) {
            ordemCompra = ordemCompraRepository.findById(dto.getOrdemCompraId())
                .orElseThrow(() -> MessageUtils.notFound("Ordem de compra", dto.getOrdemCompraId()));
        }

        insumo.setOrdemCompra(ordemCompra);
        insumo.setNomeItem(dto.getNomeItem());
        insumo.setQuantidadeTotal(dto.getQuantidadeTotal());
        insumo.setQuantidadeDisponivel(dto.getQuantidadeDisponivel());

        EstoqueInsumo atualizado = estoqueInsumoRepository.save(insumo);
        return toResponseDTO(atualizado);
    }

    @Override
    @Transactional
    public void deletar(Long id) {
        if (!estoqueInsumoRepository.existsById(id)) {
            throw MessageUtils.notFound("Estoque de insumo", id);
        }
        estoqueInsumoRepository.deleteById(id);
    }

    private EstoqueInsumoResponseDTO toResponseDTO(EstoqueInsumo insumo) {
        String ordemNumero = insumo.getOrdemCompra() != null ? insumo.getOrdemCompra().getNumeroOc() : null;
        Long ordemId = insumo.getOrdemCompra() != null ? insumo.getOrdemCompra().getId() : null;
        return new EstoqueInsumoResponseDTO(
            insumo.getId(),
            ordemId,
            ordemNumero,
            insumo.getNomeItem(),
            insumo.getQuantidadeTotal(),
            insumo.getQuantidadeDisponivel()
        );
    }
}