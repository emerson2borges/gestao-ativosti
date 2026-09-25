package com.ativosti.controller;

import com.ativosti.dto.HistoricoAtivoRequestDTO;
import com.ativosti.dto.HistoricoAtivoResponseDTO;
import com.ativosti.service.HistoricoAtivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historico-ativos")
@CrossOrigin(origins = "http://localhost:4200")
public class HistoricoAtivoController {

    @Autowired
    private HistoricoAtivoService historicoAtivoService;

    @GetMapping
    public List<HistoricoAtivoResponseDTO> listar() {
        return historicoAtivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public HistoricoAtivoResponseDTO buscarPorId(@PathVariable Long id) {
        return historicoAtivoService.buscarPorId(id);
    }

    @GetMapping("/por-ativo/{ativoId}")
    public List<HistoricoAtivoResponseDTO> buscarPorAtivo(@PathVariable Long ativoId) {
        return historicoAtivoService.buscarPorAtivo(ativoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoAtivoResponseDTO registrarAlteracao(@Valid @RequestBody HistoricoAtivoRequestDTO dto) {
        return historicoAtivoService.registrarAlteracao(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        historicoAtivoService.deletar(id);
    }
}