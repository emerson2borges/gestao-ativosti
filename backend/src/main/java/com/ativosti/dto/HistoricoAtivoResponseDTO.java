package com.ativosti.dto;

import java.time.LocalDateTime;

public class HistoricoAtivoResponseDTO {

    private Long id;
    private Long ativoId;
    private String ativoPatrimonio;
    private String campoAlterado;
    private String valorAntigo;
    private String valorNovo;
    private String chamadoGlpi;
    private LocalDateTime dataAlteracao;
    private Long usuarioId;

    public HistoricoAtivoResponseDTO() {}

    public HistoricoAtivoResponseDTO(Long id, Long ativoId, String ativoPatrimonio, String campoAlterado,
                                     String valorAntigo, String valorNovo, String chamadoGlpi,
                                     LocalDateTime dataAlteracao, Long usuarioId) {
        this.id = id;
        this.ativoId = ativoId;
        this.ativoPatrimonio = ativoPatrimonio;
        this.campoAlterado = campoAlterado;
        this.valorAntigo = valorAntigo;
        this.valorNovo = valorNovo;
        this.chamadoGlpi = chamadoGlpi;
        this.dataAlteracao = dataAlteracao;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAtivoId() { return ativoId; }
    public void setAtivoId(Long ativoId) { this.ativoId = ativoId; }

    public String getAtivoPatrimonio() { return ativoPatrimonio; }
    public void setAtivoPatrimonio(String ativoPatrimonio) { this.ativoPatrimonio = ativoPatrimonio; }

    public String getCampoAlterado() { return campoAlterado; }
    public void setCampoAlterado(String campoAlterado) { this.campoAlterado = campoAlterado; }

    public String getValorAntigo() { return valorAntigo; }
    public void setValorAntigo(String valorAntigo) { this.valorAntigo = valorAntigo; }

    public String getValorNovo() { return valorNovo; }
    public void setValorNovo(String valorNovo) { this.valorNovo = valorNovo; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}