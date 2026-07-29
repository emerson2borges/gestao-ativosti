package com.ativosti.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "HISTORICO_ATIVOS")
public class HistoricoAtivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ativo_id", nullable = false)
    private Ativo ativo;

    @Column(name = "campo_alterado", nullable = false, length = 50)
    private String campoAlterado;

    @Column(name = "valor_antigo", length = 150)
    private String valorAntigo;

    @Column(name = "valor_novo", length = 150)
    private String valorNovo;

    @Column(name = "chamado_glpi", length = 20)
    private String chamadoGlpi;

    @Column(name = "data_alteracao", nullable = false)
    private LocalDateTime dataAlteracao;

    @Column(name = "usuario_id", nullable = false)
    private Long usuarioId; // temporário, depois substituímos pela entidade Usuario

    // Construtores
    public HistoricoAtivo() {
        this.dataAlteracao = LocalDateTime.now();
    }

    // Getters e Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Ativo getAtivo() { return ativo; }
    public void setAtivo(Ativo ativo) { this.ativo = ativo; }

    public String getCampoAlterado() { return campoAlterado; }
    public void setCampoAlterado(String campoAlterado) { this.campoAlterado = campoAlterado; }

    public String getValorAntigo() { return valorAntigo; }
    public void setValorAntigo(String valorAntigo) { this.valorAntigo = valorAntigo; }

    public String getValorNovo() { return valorNovo; }
    public void setValorNovo(String valorNovo) { this.valorNovo = valorNovo; }

    public String getChamadoGlpi() { return chamadoGlpi; }
    public void setChamadoGlpi(String chamadoGlpi) { this.chamadoGlpi = chamadoGlpi; }

    public LocalDateTime getDataAlteracao() { return dataAlteracao; }
    public void setDataAlteracao(LocalDateTime dataAlteracao) { this.dataAlteracao = dataAlteracao; }

    public Long getUsuarioId() { return usuarioId; }
    public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
}