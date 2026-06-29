package com.ativosti.controller;

import com.ativosti.dto.SubativoInternoRequestDTO;
import com.ativosti.dto.SubativoInternoResponseDTO;
import com.ativosti.service.SubativoInternoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subativos-internos")
@CrossOrigin(origins = "http://localhost:4200")
public class SubativoInternoController {

    @Autowired
    private SubativoInternoService subativoInternoService;

    @GetMapping
    public List<SubativoInternoResponseDTO> listar() {
        return subativoInternoService.listarTodos();
    }

    @GetMapping("/{id}")
    public SubativoInternoResponseDTO buscarPorId(@PathVariable Long id) {
        return subativoInternoService.buscarPorId(id);
    }

    @GetMapping("/por-ativo/{ativoId}")
    public List<SubativoInternoResponseDTO> buscarPorAtivo(@PathVariable Long ativoId) {
        return subativoInternoService.buscarPorAtivo(ativoId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SubativoInternoResponseDTO criar(@Valid @RequestBody SubativoInternoRequestDTO dto) {
        return subativoInternoService.criar(dto);
    }

    @PutMapping("/{id}")
    public SubativoInternoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody SubativoInternoRequestDTO dto) {
        return subativoInternoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        subativoInternoService.deletar(id);
    }
}