package com.ativosti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class SubativoInternoRequestDTO {

    @NotNull(message = "ID do ativo é obrigatório")
    private Long ativoId;

    @NotBlank(message = "Tipo do componente é obrigatório")
    @Size(max = 50, message = "Tipo deve ter no máximo 50 caracteres")
    private String tipoComponente;

    @NotBlank(message = "Especificação é obrigatória")
    @Size(max = 150, message = "Especificação deve ter no máximo 150 caracteres")
    private String especificacao;

    @NotNull(message = "Quantidade é obrigatória")
    @Positive(message = "Quantidade deve ser maior que zero")
    private Integer quantidade;

    @Size(max = 20, message = "Chamado GLPI deve ter no máximo 20 caracteres")
    private String chamadoGlpi;

    // Getters e Setters
    public Long getAtivoId() { return ativoId; }
    public void setAtivoId(Long ativoId) { this.ativoId = ativoId; }

    public String getTipoComponente() { return tipoComponente; }
    public void setTipoComponente(String tipoComponente) { this.tipoComponente = tipoComponente; }

    public String getEspecificacao() { return especificacao; }
    public void setEspecificacao(String especificacao) { this.especificacao = especificacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }
}