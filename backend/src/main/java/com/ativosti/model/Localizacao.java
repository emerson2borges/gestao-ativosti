package com.ativosti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "LOCALIZACOES")
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;

    @Column(name = "tipo_ponto", nullable = false, length = 50)
    private String tipoPonto;

    @Column(name = "nome_ponto", nullable = false, length = 100)
    private String nomePonto;

    public Localizacao() {}

    public Localizacao(Cidade cidade, String tipoPonto, String nomePonto) {
        this.cidade = cidade;
        this.tipoPonto = tipoPonto;
        this.nomePonto = nomePonto;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Cidade getCidade() { return cidade; }
    public void setCidade(Cidade cidade) { this.cidade = cidade; }

    public String getTipoPonto() { return tipoPonto; }
    public void setTipoPonto(String tipoPonto) { this.tipoPonto = tipoPonto; }

    public String getNomePonto() { return nomePonto; }
    public void setNomePonto(String nomePonto) { this.nomePonto = nomePonto; }
}