package com.ativosti.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;

public class OrdemCompraRequestDTO {

    @NotBlank(message = "Número da OC é obrigatório")
    @Size(max = 50, message = "Número da OC deve ter no máximo 50 caracteres")
    private String numeroOc;

    @NotNull(message = "Data da compra é obrigatória")
    private LocalDate dataCompra;

    @NotBlank(message = "Fornecedor é obrigatório")
    @Size(max = 150, message = "Fornecedor deve ter no máximo 150 caracteres")
    private String fornecedor;

    @Size(max = 255, message = "Caminho do arquivo deve ter no máximo 255 caracteres")
    private String arquivoPath;

    // Getters e Setters
    public String getNumeroOc() { return numeroOc; }
    public void setNumeroOc(String numeroOc) { this.numeroOc = numeroOc; }

    public LocalDate getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDate dataCompra) { this.dataCompra = dataCompra; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getArquivoPath() { return arquivoPath; }
    public void setArquivoPath(String arquivoPath) { this.arquivoPath = arquivoPath; }
}