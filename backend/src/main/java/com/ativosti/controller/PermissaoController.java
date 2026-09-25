package com.ativosti.controller;

import com.ativosti.dto.PermissaoResponseDTO;
import com.ativosti.service.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/permissoes")
@CrossOrigin(origins = "http://localhost:4200")
public class PermissaoController {

    @Autowired
    private PermissaoService permissaoService;

    @GetMapping
    public List<PermissaoResponseDTO> listar() {
        return permissaoService.listarTodas();
    }
}
