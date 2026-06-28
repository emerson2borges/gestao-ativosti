package com.ativosti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class AtivoRequestDTO {

    @NotNull(message = "Tipo de ativo é obrigatório")
    private Long tipoId;

    @NotNull(message = "Localização é obrigatória")
    private Long localizacaoId;

    private Long ordemCompraId;

    @NotBlank(message = "Patrimônio é obrigatório")
    @Size(max = 50, message = "Patrimônio deve ter no máximo 50 caracteres")
    private String patrimonio;

    @Size(max = 50, message = "Hostname deve ter no máximo 50 caracteres")
    private String hostnameAtual;

    @Size(max = 100, message = "Responsável deve ter no máximo 100 caracteres")
    private String responsavel;

    @NotBlank(message = "Status é obrigatório")
    @Size(max = 30, message = "Status deve ter no máximo 30 caracteres")
    private String status;

    // Getters e Setters
    public Long getTipoId() { return tipoId; }
    public void setTipoId(Long tipoId) { this.tipoId = tipoId; }

    public Long getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Long localizacaoId) { this.localizacaoId = localizacaoId; }

    public Long getOrdemCompraId() { return ordemCompraId; }
    public void setOrdemCompraId(Long ordemCompraId) { this.ordemCompraId = ordemCompraId; }

    public String getPatrimonio() { return patrimonio; }
    public void setPatrimonio(String patrimonio) { this.patrimonio = patrimonio; }

    public String getHostnameAtual() { return hostnameAtual; }
    public void setHostnameAtual(String hostnameAtual) { this.hostnameAtual = hostnameAtual; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}