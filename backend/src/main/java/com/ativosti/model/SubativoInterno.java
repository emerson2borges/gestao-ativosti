package com.ativosti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "SUBATIVOS_INTERNO")
public class SubativoInterno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;

    @Column(name = "tipo_componente", nullable = false, length = 50)
    private String tipoComponente;

    @Column(nullable = false, length = 150)
    private String especificacao;

    @Column(nullable = false)
    private Integer quantidade = 1;

    @Column(name = "chamado_glpi", length = 20)
    private String chamadoGlpi;

    // Construtores
    public SubativoInterno() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Ativo getAtivo() { return ativo; }
    public void setAtivo(Ativo ativo) { this.ativo = ativo; }

    public String getTipoComponente() { return tipoComponente; }
    public void setTipoComponente(String tipoComponente) { this.tipoComponente = tipoComponente; }

    public String getEspecificacao() { return especificacao; }
    public void setEspecificacao(String especificacao) { this.especificacao = especificacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }
}