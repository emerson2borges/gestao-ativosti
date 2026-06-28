package com.ativosti.dto;

import java.time.LocalDate;

public class OrdemCompraResponseDTO {

    private Long id;
    private String numeroOc;
    private LocalDate dataCompra;
    private String fornecedor;
    private String arquivoPath;

    public OrdemCompraResponseDTO() {}

    public OrdemCompraResponseDTO(Long id, String numeroOc, LocalDate dataCompra, String fornecedor, String arquivoPath) {
        this.id = id;
        this.numeroOc = numeroOc;
        this.dataCompra = dataCompra;
        this.fornecedor = fornecedor;
        this.arquivoPath = arquivoPath;
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroOc() { return numeroOc; }
    public void setNumeroOc(String numeroOc) { this.numeroOc = numeroOc; }

    public LocalDate getDataCompra() { return dataCompra; }
    public void setDataCompra(LocalDate dataCompra) { this.dataCompra = dataCompra; }

    public String getFornecedor() { return fornecedor; }
    public void setFornecedor(String fornecedor) { this.fornecedor = fornecedor; }

    public String getArquivoPath() { return arquivoPath; }
    public void setArquivoPath(String arquivoPath) { this.arquivoPath = arquivoPath; }
}