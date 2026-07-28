package com.ativosti.controller;

import com.ativosti.dto.HistoricoInsumoRequestDTO;
import com.ativosti.dto.HistoricoInsumoResponseDTO;
import com.ativosti.service.HistoricoInsumoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico-insumos")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricoInsumoController {

    @Autowired
    private HistoricoInsumoService historicoInsumoService;

    @GetMapping
    public List<HistoricoInsumoResponseDTO> listar() {
        return historicoInsumoService.listarTodos();
    }

    @GetMapping("/{id}")
    public HistoricoInsumoResponseDTO buscarPorId(@PathVariable Long id) {
        return historicoInsumoService.buscarPorId(id);
    }

    @GetMapping("/por-insumo/{insumoId}")
    public List<HistoricoInsumoResponseDTO> buscarPorInsumo(@PathVariable Long insumoId) {
        return historicoInsumoService.buscarPorInsumo(insumoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoInsumoResponseDTO registrarMovimentacao(@Valid @RequestBody HistoricoInsumoRequestDTO dto) {
        return historicoInsumoService.registrarMovimentacao(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        historicoInsumoService.deletar(id);
    }
}