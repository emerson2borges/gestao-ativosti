package com.ativosti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ATIVOS")
public class Ativo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "tipo_id", nullable = false)
    private TipoAtivo tipo;

    @ManyToOne
    @JoinColumn(name = "localizacao_id", nullable = false)
    private Localizacao localizacao;

    @ManyToOne
    @JoinColumn(name = "ordem_compra_id")
    private OrdemCompra ordemCompra;

    @Column(nullable = false, unique = true, length = 50)
    private String patrimonio;

    @Column(name = "hostname_atual", length = 50)
    private String hostnameAtual;

    @Column(length = 100)
    private String responsavel;

    @Column(nullable = false, length = 30)
    private String status;

    // Construtores
    public Ativo() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public TipoAtivo getTipo() { return tipo; }
    public void setTipo(TipoAtivo tipo) { this.tipo = tipo; }

    public Localizacao getLocalizacao() { return localizacao; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public OrdemCompra getOrdemCompra() { return ordemCompra; }
    public void setOrdemCompra(OrdemCompra ordemCompra) { this.ordemCompra = ordemCompra; }

    public String getPatrimonio() { return patrimonio; }
    public void setPatrimonio(String patrimonio) { this.patrimonio = patrimonio; }

    public String getHostnameAtual() { return hostnameAtual; }
    public void setHostnameAtual(String hostnameAtual) { this.hostnameAtual = hostnameAtual; }

    public String getResponsavel() { return responsavel; }
    public void setResponsavel(String responsavel) { this.responsavel = responsavel; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}