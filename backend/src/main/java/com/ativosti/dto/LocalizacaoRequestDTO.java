package com.ativosti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class LocalizacaoRequestDTO {

    @NotNull(message = "ID da cidade é obrigatório")
    private Long cidadeId;

    @NotBlank(message = "Tipo de ponto é obrigatório")
    @Size(max = 50, message = "Máximo 50 caracteres")
    private String tipoPonto;

    @NotBlank(message = "Nome do ponto é obrigatório")
    @Size(max = 100, message = "Máximo 100 caracteres")
    private String nomePonto;

    // Construtores, getters e setters
    public LocalizacaoRequestDTO() {}

    public Long getCidadeId() { return cidadeId; }
    public void setCidadeId(Long cidadeId) { this.cidadeId = cidadeId; }

    public String getTipoPonto() { return tipoPonto; }
    public void setTipoPonto(String tipoPonto) { this.tipoPonto = tipoPonto; }

    public String getNomePonto() { return nomePonto; }
    public void setNomePonto(String nomePonto) { this.nomePonto = nomePonto; }
}