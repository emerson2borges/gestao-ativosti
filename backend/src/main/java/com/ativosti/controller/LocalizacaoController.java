package com.ativosti.controller;

import com.ativosti.dto.LocalizacaoRequestDTO;
import com.ativosti.dto.LocalizacaoResponseDTO;
import com.ativosti.service.LocalizacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/localizacoes")
@CrossOrigin(origins = "http://localhost:4200")
public class LocalizacaoController {

    @Autowired
    private LocalizacaoService localizacaoService;

    @GetMapping
    public List<LocalizacaoResponseDTO> listar() {
        return localizacaoService.listarTodas();
    }

    @GetMapping("/{id}")
    public LocalizacaoResponseDTO buscarPorId(@PathVariable Long id) {
        return localizacaoService.buscarPorId(id);
    }

    @GetMapping("/por-cidade/{cidadeId}")
    public List<LocalizacaoResponseDTO> buscarPorCidade(@PathVariable Long cidadeId) {
        return localizacaoService.buscarPorCidade(cidadeId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LocalizacaoResponseDTO criar(@Valid @RequestBody LocalizacaoRequestDTO dto) {
        return localizacaoService.criar(dto);
    }

    @PutMapping("/{id}")
    public LocalizacaoResponseDTO atualizar(@PathVariable Long id, @Valid @RequestBody LocalizacaoRequestDTO dto) {
        return localizacaoService.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        localizacaoService.deletar(id);
    }
}