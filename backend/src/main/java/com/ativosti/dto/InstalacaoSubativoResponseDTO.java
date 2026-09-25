package com.ativosti.dto;

import com.ativosti.dto.InstalacaoSubativoResponseDTO;

public class InstalacaoSubativoResponseDTO {

    private Long ativoId;
    private String ativoPatrimonio;
    private Long subativoId;
    private String subativoTipo;
    private String subativoEspecificacao;
    private Integer quantidadeConsumida;
    private Integer estoqueRestante;
    private String chamadoGlpi;

    // Construtor padrão
    public InstalacaoSubativoResponseDTO() {}

    // Construtor com todos os campos
    public InstalacaoSubativoResponseDTO(Long ativoId, String ativoPatrimonio, Long subativoId,
                                         String subativoTipo, String subativoEspecificacao,
                                         Integer quantidadeConsumida, Integer estoqueRestante,
                                         String chamadoGlpi) {
        this.ativoId = ativoId;
        this.ativoPatrimonio = ativoPatrimonio;
        this.subativoId = subativoId;
        this.subativoTipo = subativoTipo;
        this.subativoEspecificacao = subativoEspecificacao;
        this.quantidadeConsumida = quantidadeConsumida;
        this.estoqueRestante = estoqueRestante;
        this.chamadoGlpi = chamadoGlpi;
    }

    // Getters e Setters
    public Long getAtivoId() {
        return ativoId;
    }

    public void setAtivoId(Long ativoId) {
        this.ativoId = ativoId;
    }

    public String getAtivoPatrimonio() {
        return ativoPatrimonio;
    }

    public void setAtivoPatrimonio(String ativoPatrimonio) {
        this.ativoPatrimonio = ativoPatrimonio;
    }

    public Long getSubativoId() {
        return subativoId;
    }

    public void setSubativoId(Long subativoId) {
        this.subativoId = subativoId;
    }

    public String getSubativoTipo() {
        return subativoTipo;
    }

    public void setSubativoTipo(String subativoTipo) {
        this.subativoTipo = subativoTipo;
    }

    public String getSubativoEspecificacao() {
        return subativoEspecificacao;
    }

    public void setSubativoEspecificacao(String subativoEspecificacao) {
        this.subativoEspecificacao = subativoEspecificacao;
    }

    public Integer getQuantidadeConsumida() {
        return quantidadeConsumida;
    }

    public void setQuantidadeConsumida(Integer quantidadeConsumida) {
        this.quantidadeConsumida = quantidadeConsumida;
    }

    public Integer getEstoqueRestante() {
        return estoqueRestante;
    }

    public void setEstoqueRestante(Integer estoqueRestante) {
        this.estoqueRestante = estoqueRestante;
    }

    public String getChamadoGlpi() {
        return chamadoGlpi;
    }

    public void setChamadoGlpi(String chamadoGlpi) {
        this.chamadoGlpi = chamadoGlpi;
    }
}