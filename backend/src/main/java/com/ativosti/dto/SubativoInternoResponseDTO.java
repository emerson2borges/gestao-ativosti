package com.ativosti.dto;

public class SubativoInternoResponseDTO {

    private Long id;
    private Long ativoId;
    private String ativoPatrimonio;
    private String tipoComponente;
    private String especificacao;
    private Integer quantidade;
    private String chamadoGlpi;

    public SubativoInternoResponseDTO() {}

    public SubativoInternoResponseDTO(Long id, Long ativoId, String ativoPatrimonio, String tipoComponente,
                                      String especificacao, Integer quantidade, String chamadoGlpi) {
        this.id = id;
        this.ativoId = ativoId;
        this.ativoPatrimonio = ativoPatrimonio;
        this.tipoComponente = tipoComponente;
        this.especificacao = especificacao;
        this.quantidade = quantidade;
        this.chamadoGlpi = chamadoGlpi;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getAtivoId() { return ativoId; }
    public void setAtivoId(Long ativoId) { this.ativoId = ativoId; }

    public String getAtivoPatrimonio() { return ativoPatrimonio; }
    public void setAtivoPatrimonio(String ativoPatrimonio) { this.ativoPatrimonio = ativoPatrimonio; }

    public String getTipoComponente() { return tipoComponente; }
    public void setTipoComponente(String tipoComponente) { this.tipoComponente = tipoComponente; }

    public String getEspecificacao() { return especificacao; }
    public void setEspecificacao(String especificacao) { this.especificacao = especificacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }
}