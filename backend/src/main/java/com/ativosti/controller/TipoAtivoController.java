package com.ativosti.controller;

import com.ativosti.dto.TipoAtivoRequestDTO;
import com.ativosti.dto.TipoAtivoResponseDTO;
import com.ativosti.service.TipoAtivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos-ativo")
@CrossOrigin(origins = "http://localhost:4200")
public class TipoAtivoController {

    @Autowired
    private TipoAtivoService tipoAtivoService;

    @GetMapping
    public List<TipoAtivoResponseDTO> listar() {
        return tipoAtivoService.listarTodos();
    }

    @GetMapping("/{id}")
    public TipoAtivoResponseDTO buscarPorId(@PathVariable Long id) {
        return tipoAtivoService.buscarPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TipoAtivoResponseDTO criar(@Valid @RequestBody TipoAtivoRequestDTO dto) {
        return tipoAtivoService.criar(dto);
    }

    @PutMapping("/{id}")
    public TipoAtivoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody TipoAtivoRequestDTO dto) {
        return tipoAtivoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        tipoAtivoService.deletar(id);
    }
}