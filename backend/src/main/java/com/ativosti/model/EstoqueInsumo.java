package com.ativosti.model;

import jakarta.persistence.*;

@Entity
@Table(name = "ESTOQUE_INSUMOS")
public class EstoqueInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ordem_compra_id")
    private OrdemCompra ordemCompra;

    @Column(name = "nome_item", nullable = false, length = 100)
    private String nomeItem;

    @Column(name = "quantidade_total", nullable = false)
    private Integer quantidadeTotal = 0;

    @Column(name = "quantidade_disponivel", nullable = false)
    private Integer quantidadeDisponivel = 0;

    // Construtores
    public EstoqueInsumo() {}

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public OrdemCompra getOrdemCompra() { return ordemCompra; }
    public void setOrdemCompra(OrdemCompra ordemCompra) { this.ordemCompra = ordemCompra; }

    public String getNomeItem() { return nomeItem; }
    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Integer quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
}