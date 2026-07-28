package com.ativosti.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORICO_INSUMOS")
public class HistoricoInsumo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "insumo_id", nullable = false)
    private EstoqueInsumo insumo;

    @Column(name = "tipo_movimentacao", nullable = false, length = 10)
    private String tipoMovimentacao;

    @ManyToOne
    @JoinColumn(name = "localizacao_id")
    private Localizacao localizacao;

    @Column(nullable = false)
    private Integer quantidade;

    @Column(name = "chamado_glpi", length = 20)
    private String chamadoGlpi;

    @Column(name = "data_movimentacao", nullable = false)
    private LocalDateTime dataMovimentacao;

    // @ManyToOne
    // @JoinColumn(name = "usuario_id", nullable = false)
    // private Usuario usuario;
    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId;

    // Construtores
    public HistoricoInsumo() {
        this.dataMovimentacao = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public EstoqueInsumo getInsumo() { return insumo; }
    public void setInsumo(EstoqueInsumo insumo) { this.insumo = insumo; }

    public String getTipoMovimentacao() { return tipoMovimentacao; }
    public void setTipoMovimentacao(String tipoMovimentacao) { this.tipoMovimentacao = tipoMovimentacao; }

    public Localizacao getLocalizacao() { return localizacao; }
    public void setLocalizacao(Localizacao localizacao) { this.localizacao = localizacao; }

    public Integer getQuantidade() { return quantidade; }
    public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }

    public LocalDateTime getDataMovimentacao() { return dataMovimentacao; }
    public void setDataMovimentacao(LocalDateTime dataMovimentacao) { this.dataMovimentacao = dataMovimentacao; }

    // public Usuario getUsuario() { return usuario; }
    // public void setUsuario(Usuario usuario) { this.usuario = usuario; }
    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}