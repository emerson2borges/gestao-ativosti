package com.ativosti.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class EstoqueInsumoRequestDTO {

    private Long ordemCompraId;

    @NotBlank(message = "Nome do item é obrigatório")
    @Size(max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String nomeItem;

    @NotNull(message = "Quantidade total é obrigatória")
    @Min(value = 0, message = "Quantidade total não pode ser negativa")
    private Integer quantidadeTotal;

    @NotNull(message = "Quantidade disponível é obrigatória")
    @Min(value = 0, message = "Quantidade disponível não pode ser negativa")
    private Integer quantidadeDisponivel;

    // Getters e Setters
    public Long getOrdemCompraId() { return ordemCompraId; }
    public void setOrdemCompraId(Long ordemCompraId) { this.ordemCompraId = ordemCompraId; }

    public String getNomeItem() { return nomeItem; }
    public void setNomeItem(String nomeItem) { this.nomeItem = nomeItem; }

    public Integer getQuantidadeTotal() { return quantidadeTotal; }
    public void setQuantidadeTotal(Integer quantidadeTotal) { this.quantidadeTotal = quantidadeTotal; }

    public Integer getQuantidadeDisponivel() { return quantidadeDisponivel; }
    public void setQuantidadeDisponivel(Integer quantidadeDisponivel) { this.quantidadeDisponivel = quantidadeDisponivel; }
}