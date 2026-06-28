package com.ativosti.controller;

import com.ativosti.dto.AtivoRequestDTO;
import com.ativosti.dto.AtivoResponseDTO;
import com.ativosti.service.AtivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ativos")
@CrossOrigin(origins = "http://localhost:4200")
public class AtivoController {

    @Autowired
    private AtivoService ativoService;

    @GetMapping
    public List<AtivoResponseDTO> listar() {
        return ativoService.listarTodos();
    }

    @GetMapping("/{id}")
    public AtivoResponseDTO buscarPorId(@PathVariable Long id) {
        return ativoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtivoResponseDTO criar(@Valid @RequestBody AtivoRequestDTO dto) {
        return ativoService.criar(dto);
    }

    @PutMapping("/{id}")
    public AtivoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody AtivoRequestDTO dto) {
        return ativoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        ativoService.deletar(id);
    }
}