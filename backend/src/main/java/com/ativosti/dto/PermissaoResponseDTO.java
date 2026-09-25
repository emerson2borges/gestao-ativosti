package com.ativosti.dto;

public class PermissaoResponseDTO {
    private Long id;
    private String chave;
    private String descricao;

    public PermissaoResponseDTO() {}

    public PermissaoResponseDTO(Long id, String chave, String descricao) {
        this.id = id;
        this.chave = chave;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
