package com.ativosti.controller;

import com.ativosti.dto.OrdemCompraRequestDTO;
import com.ativosti.dto.OrdemCompraResponseDTO;
import com.ativosti.service.OrdemCompraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ordens-compra")
@CrossOrigin(origins = "http://localhost:4200")
public class OrdemCompraController {

    @Autowired
    private OrdemCompraService ordemCompraService;

    @GetMapping
    public List<OrdemCompraResponseDTO> listar() {
        return ordemCompraService.listarTodos();
    }

    @GetMapping("/{id}")
    public OrdemCompraResponseDTO buscarPorId(@PathVariable Long id) {
        return ordemCompraService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemCompraResponseDTO criar(@Valid @RequestBody OrdemCompraRequestDTO dto) {
        return ordemCompraService.criar(dto);
    }

    @PutMapping("/{id}")
    public OrdemCompraResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody OrdemCompraRequestDTO dto) {
        return ordemCompraService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        ordemCompraService.deletar(id);
    }
}