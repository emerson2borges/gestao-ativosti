package com.ativosti.dto;

public class AtivoResponseDTO {

    private Long id;
    private String patrimonio;
    private String hostnameAtual;
    private String responsavel;
    private String status;

    private Long tipoId;
    private String tipoNome;

    private Long localizacaoId;
    private String localizacaoNome;

    private Long ordemCompraId;
    private String ordemCompraNumero;

    // Construtores
    public AtivoResponseDTO() {}

    public AtivoResponseDTO(Long id, String patrimonio, String hostnameAtual, String responsavel, String status,
                            Long tipoId, String tipoNome, Long localizacaoId, String localizacaoNome,
                            Long ordemCompraId, String ordemCompraNumero) {
        this.id = id;
        this.patrimonio = patrimonio;
        this.hostnameAtual = hostnameAtual;
        this.responsavel = responsavel;
        this.status = status;
        this.tipoId = tipoId;
        this.tipoNome = tipoNome;
        this.localizacaoId = localizacaoId;
        this.localizacaoNome = localizacaoNome;
        this.ordemCompraId = ordemCompraId;
        this.ordemCompraNumero = ordemCompraNumero;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPatrimonio() {
        return patrimonio;
    }

    public void setPatrimonio(String patrimonio) {
        this.patrimonio = patrimonio;
    }

    public String getHostnameAtual() {
        return hostnameAtual;
    }

    public void setHostnameAtual(String hostnameAtual) {
        this.hostnameAtual = hostnameAtual;
    }

    public String getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(String responsavel) {
        this.responsavel = responsavel;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getTipoId() {
        return tipoId;
    }

    public void setTipoId(Long tipoId) {
        this.tipoId = tipoId;
    }

    public String getTipoNome() {
        return tipoNome;
    }

    public void setTipoNome(String tipoNome) {
        this.tipoNome = tipoNome;
    }

    public Long getLocalizacaoId() {
        return localizacaoId;
    }

    public void setLocalizacaoId(Long localizacaoId) {
        this.localizacaoId = localizacaoId;
    }

    public String getLocalizacaoNome() {
        return localizacaoNome;
    }

    public void setLocalizacaoNome(String localizacaoNome) {
        this.localizacaoNome = localizacaoNome;
    }

    public Long getOrdemCompraId() {
        return ordemCompraId;
    }

    public void setOrdemCompraId(Long ordemCompraId) {
        this.ordemCompraId = ordemCompraId;
    }

    public String getOrdemCompraNumero() {
        return ordemCompraNumero;
    }

    public void setOrdemCompraNumero(String ordemCompraNumero) {
        this.ordemCompraNumero = ordemCompraNumero;
    }
}