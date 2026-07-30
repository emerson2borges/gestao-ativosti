package com.ativosti.dto;

import java.time.LocalDateTime;

public class HistoricoInsumoResponseDTO {

    private Long id;
    private Long insumoId;
    private String insumoNome;
    private String tipoMovimentacao;
    private Long localizacaoId;
    private String localizacaoNome;
    private Integer quantidade;
    private String chamadoGlpi;
    private LocalDateTime dataMovimentacao;
    private Long usuarioId;
    private String usuarioNome;

    public HistoricoInsumoResponseDTO() {}

    // Construtor completo
    public HistoricoInsumoResponseDTO(Long id, Long insumoId, String insumoNome, String tipoMovimentacao,
                                      Long localizacaoId, String localizacaoNome, Integer quantidade,
                                      String chamadoGlpi, LocalDateTime dataMovimentacao,
                                      Long usuarioId, String usuarioNome) {
        this.id = id;
        this.insumoId = insumoId;
        this.insumoNome = insumoNome;
        this.tipoMovimentacao = tipoMovimentacao;
        this.localizacaoId = localizacaoId;
        this.localizacaoNome = localizacaoNome;
        this.quantidade = quantidade;
        this.chamadoGlpi = chamadoGlpi;
        this.dataMovimentacao = dataMovimentacao;
        this.usuarioId = usuarioId;
        this.usuarioNome = usuarioNome;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getInsumoId() { return insumoId; }
    public void setInsumoId(Long insumoId) { this.insumoId = insumoId; }

    public String getInsumoNome() { return insumoNome; }
    public void setInsumoNome(String insumoNome) { this.insumoNome = insumoNome; }

    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public Long getLocalizacaoId() { return localizacaoId; }
    public void setLocalizacaoId(Long localizacaoId) { this.localizacaoId = localizacaoId; }

    public String getLocalizacaoNome() { return localizacaoNome; }
    public void setLocalizacaoNome(String localizacaoNome) { this.localizacaoNome = localizacaoNome; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }

    public String getUsuarioNome() { return usuarioNome; }
    public void setUsuarioNome(String usuarioNome) { this.usuarioNome = usuarioNome; }
}