package com.ativosti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "PERMISSOES")
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true, length = 50)
    private String chave;

    @Column(nullable = false, length = 100)
    private String descricao;

    public Permissao() {}

    public Permissao(String chave, String descricao) {
        this.chave = chave;
        this.descricao = descricao;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getChave() { return chave; }
    public void setChave(String chave) { this.chave = chave; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
}
