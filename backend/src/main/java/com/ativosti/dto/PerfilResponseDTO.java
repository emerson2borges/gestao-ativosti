package com.ativosti.dto;

import java.util.Set;

public class PerfilResponseDTO {
    private Long id;
    private String nome;
    private Set<PermissaoResponseDTO> permissoes;

    public PerfilResponseDTO() {}

    public PerfilResponseDTO(Long id, String nome, Set<PermissaoResponseDTO> permissoes) {
        this.id = id;
        this.nome = nome;
        this.permissoes = permissoes;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<PermissaoResponseDTO> getPermissoes() { return permissoes; }
    public void setPermissoes(Set<PermissaoResponseDTO> permissoes) { this.permissoes = permissoes; }
}
