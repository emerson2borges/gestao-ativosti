package com.ativosti.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ORDENS_COMPRA")
public class OrdemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_oc", nullable = false, unique = true, length = 50)
    private String numeroOc;

    @Column(name = "data_compra", nullable = false)
    private LocalDate dataCompra;

    @Column(nullable = false, length = 150)
    private String fornecedor;

    @Column(name = "arquivo_path", length = 255)
    private String arquivoPath;

    public OrdemCompra() {}

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