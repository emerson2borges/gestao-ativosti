package com.ativosti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HistoricoAtivoRequestDTO {

    @NotNull(message = "ID do ativo é obrigatório")
    private Long ativoId;

    @NotBlank(message = "Campo alterado é obrigatório")
    @Size(max = 50, message = "Campo deve ter no máximo 50 caracteres")
    private String campoAlterado;

    @Size(max = 150, message = "Valor antigo deve ter no máximo 150 caracteres")
    private String valorAntigo;

    @Size(max = 150, message = "Valor novo deve ter no máximo 150 caracteres")
    private String valorNovo;

    @Size(max = 20, message = "Chamado GLPI deve ter no máximo 20 caracteres")
    private String chamadoGlpi;

    // Getters e Setters
    public Long getAtivoId() { return ativoId; }
    public void setAtivoId(Long ativoId) { this.ativoId = ativoId; }

    public String getCampoAlterado() { return campoAlterado; }
    public void setCampoAlterado(String campoAlterado) { this.campoAlterado = campoAlterado; }

    public String getValorAntigo() { return valorAntigo; }
    public void setValorAntigo(String valorAntigo) { this.valorAntigo = valorAntigo; }

    public String getValorNovo() { return valorNovo; }
    public void setValorNovo(String valorNovo) { this.valorNovo = valorNovo; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }
}