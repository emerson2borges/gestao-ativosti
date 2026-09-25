package com.ativosti.controller;

import com.ativosti.dto.PerfilRequestDTO;
import com.ativosti.dto.PerfilResponseDTO;
import com.ativosti.service.PerfilService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/perfis")
@CrossOrigin(origins = "http://localhost:4200")
public class PerfilController {

    @Autowired
    private PerfilService perfilService;

    @GetMapping
    public List<PerfilResponseDTO> listar() {
        return perfilService.listarTodos();
    }

    @GetMapping("/{id}")
    public PerfilResponseDTO buscarPorId(@PathVariable Long id) {
        return perfilService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PerfilResponseDTO criar(@Valid @RequestBody PerfilRequestDTO dto) {
        return perfilService.criar(dto);
    }

    @PutMapping("/{id}")
    public PerfilResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody PerfilRequestDTO dto) {
        return perfilService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        perfilService.deletar(id);
    }
}
