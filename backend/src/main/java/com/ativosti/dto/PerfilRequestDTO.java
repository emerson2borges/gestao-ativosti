package com.ativosti.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.Set;

public class PerfilRequestDTO {

    @NotBlank(message = "O nome do perfil é obrigatório")
    private String nome;

    private Set<Long> permissaoIds;

    public PerfilRequestDTO() {}

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public Set<Long> getPermissaoIds() { return permissaoIds; }
    public void setPermissaoIds(Set<Long> permissaoIds) { this.permissaoIds = permissaoIds; }
}
