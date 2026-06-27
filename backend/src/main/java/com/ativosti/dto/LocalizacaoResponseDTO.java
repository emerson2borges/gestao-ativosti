package com.ativosti.dto;

public class LocalizacaoResponseDTO {

    private Long id;
    private Long cidadeId;
    private String nomeCidade;
    private String tipoPonto;
    private String nomePonto;

    public LocalizacaoResponseDTO() {}

    public LocalizacaoResponseDTO(Long id, Long cidadeId, String nomeCidade, String tipoPonto, String nomePonto) {
        this.id = id;
        this.cidadeId = cidadeId;
        this.nomeCidade = nomeCidade;
        this.tipoPonto = tipoPonto;
        this.nomePonto = nomePonto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCidadeId() { return cidadeId; }
    public void setCidadeId(Long cidadeId) { this.cidadeId = cidadeId; }

    public String getNomeCidade() { return nomeCidade; }
    public void setNomeCidade(String nomeCidade) { this.nomeCidade = nomeCidade; }

    public String getTipoPonto() { return tipoPonto; }
    public void setTipoPonto(String tipoPonto) { this.tipoPonto = tipoPonto; }

    public String getNomePonto() { return nomePonto; }
    public void setNomePonto(String nomePonto) { this.nomePonto = nomePonto; }
}