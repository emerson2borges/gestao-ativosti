
package com.ativosti.controller;

import com.ativosti.dto.CidadeRequestDTO;
import com.ativosti.dto.CidadeResponseDTO;
import com.ativosti.service.CidadeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cidades")
@CrossOrigin(origins = "http://localhost:4200")  // Libera para o frontend
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<CidadeResponseDTO> listar() {
        return cidadeService.listarTodas();
    }

    @GetMapping("/{id}")
    public CidadeResponseDTO buscarPorId(@PathVariable Long id) {
        return cidadeService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CidadeResponseDTO criar(@Valid @RequestBody CidadeRequestDTO dto) {
        return cidadeService.criar(dto);
    }

    @PutMapping("/{id}")
    public CidadeResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody CidadeRequestDTO dto) {
        return cidadeService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        cidadeService.deletar(id);
    }
}