package com.ativosti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TIPOS_ATIVO")
public class TipoAtivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String nome;

    public TipoAtivo() {}

    public TipoAtivo(String nome) {
        this.nome = nome;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
}