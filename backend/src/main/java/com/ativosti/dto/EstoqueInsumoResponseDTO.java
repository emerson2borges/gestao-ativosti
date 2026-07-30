package com.ativosti.dto;

public class EstoqueInsumoResponseDTO {

    private Long id;
    private Long ordemCompraId;
    private String ordemCompraNumero;
    private String nomeItem;
    private Integer quantidadeTotal;
    private Integer quantidadeDisponivel;

    public EstoqueInsumoResponseDTO() {}

    public EstoqueInsumoResponseDTO(
        Long id, Long ordemCompraId, String ordemCompraNumero,
        String nomeItem, Integer quantidadeTotal, Integer quantidadeDisponivel
    ) {
        this.id = id;
        this.ordemCompraId = ordemCompraId;
        this.ordemCompraNumero = ordemCompraNumero;
        this.nomeItem = nomeItem;
        this.quantidadeTotal = quantidadeTotal;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getOrdemCompraId() { return ordemCompraId; }
    public void setOrdemCompraId(Long ordemCompraId) { this.ordemCompraId = ordemCompraId; }

    public String getOrdemCompraNumero() { return ordemCompraNumero; }
    public void setOrdemCompraNumero(String ordemCompraNumero) { this.ordemCompraNumero = ordemCompraNumero; }

    public String getNomeItem() { return nomeItem; }
    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Integer quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
}