package com.ativosti.controller;

import com.ativosti.dto.EstoqueInsumoRequestDTO;
import com.ativosti.dto.EstoqueInsumoResponseDTO;
import com.ativosti.service.EstoqueInsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque-insumos")
@CrossOrigin(origins = "http://localhost:4200")
public class EstoqueInsumoController {

    @Autowired
    private EstoqueInsumoService estoqueInsumoService;

    @GetMapping
    public List<EstoqueInsumoResponseDTO> listar() {
        return estoqueInsumoService.listarTodos();
    }

    @GetMapping("/{id}")
    public EstoqueInsumoResponseDTO buscarPorId(@PathVariable Long id) {
        return estoqueInsumoService.buscarPorId(id);
    }

    @GetMapping("/por-ordem-compra/{ordemCompraId}")
    public List<EstoqueInsumoResponseDTO> buscarPorOrdemCompra(@PathVariable Long ordemCompraId) {
        return estoqueInsumoService.buscarPorOrdemCompra(ordemCompraId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EstoqueInsumoResponseDTO criar(@Valid @RequestBody EstoqueInsumoRequestDTO dto) {
        return estoqueInsumoService.criar(dto);
    }

    @PutMapping("/{id}")
    public EstoqueInsumoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody EstoqueInsumoRequestDTO dto) {
        return estoqueInsumoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        estoqueInsumoService.deletar(id);
    }
}