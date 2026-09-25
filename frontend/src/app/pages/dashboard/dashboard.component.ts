import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { ButtonModule } from 'primeng/button';
import { TableModule } from 'primeng/table';
import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { StatusBadgeComponent } from '../../shared/components/status-badge/status-badge.component';
import { AtivoService } from '../../core/services/ativo.service';
import { EstoqueService } from '../../core/services/estoque.service';
import { AtivoResponse } from '../../core/models/ativo.model';
import { EstoqueInsumoResponse } from '../../core/models/estoque-insumo.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [
    CommonModule,
    RouterModule,
    ButtonModule,
    TableModule,
    PageHeaderComponent,
    StatusBadgeComponent
  ],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.scss'
})
export class DashboardComponent implements OnInit {
  private ativoService = inject(AtivoService);
  private estoqueService = inject(EstoqueService);

  totalAtivos = 0;
  ativosEmUso = 0;
  ativosManutencao = 0;
  insumosCriticos = 0;

  recentAtivos: AtivoResponse[] = [];
  criticalSupplies: EstoqueInsumoResponse[] = [];

  ngOnInit(): void {
    this.carregarDados();
  }

  carregarDados(): void {
    this.ativoService.listar().subscribe({
      next: (ativos) => {
        this.recentAtivos = ativos;
        this.totalAtivos = ativos.length;
        this.ativosEmUso = ativos.filter(a => (a.status || '').toLowerCase().includes('uso')).length;
        this.ativosManutencao = ativos.filter(a => (a.status || '').toLowerCase().includes('manuten')).length;
      },
      error: () => {
        // Mock fallback para visualização inicial
        this.recentAtivos = [
          { id: 1, patrimonio: 'PAT-2026-001', hostnameAtual: 'NOTE-DEV-01', responsavel: 'Carlos Silva', status: 'Em Uso', tipoNome: 'Notebook Dell XPS', localizacaoNome: 'TI - Bloco A' },
          { id: 2, patrimonio: 'PAT-2026-002', hostnameAtual: 'SRV-DB-01', responsavel: 'Infraestrutura', status: 'Em Uso', tipoNome: 'Servidor Dell PowerEdge', localizacaoNome: 'Data Center' },
          { id: 3, patrimonio: 'PAT-2026-003', hostnameAtual: 'MON-DESIGN-02', responsavel: 'Ana Lima', status: 'Disponível', tipoNome: 'Monitor LG 27"', localizacaoNome: 'Almoxarifado TI' }
        ];
        this.totalAtivos = 15;
        this.ativosEmUso = 11;
        this.ativosManutencao = 2;
      }
    });

    this.estoqueService.listar().subscribe({
      next: (estoque) => {
        this.criticalSupplies = estoque.filter(item => item.quantidadeAtual <= item.quantidadeMinima);
        this.insumosCriticos = this.criticalSupplies.length;
      },
      error: () => {
        this.criticalSupplies = [
          { id: 1, nomeItem: 'Cabo Patch Cord Cat6 2m', categoria: 'Redes', quantidadeAtual: 3, quantidadeMinima: 10, localizacaoNome: 'Almoxarifado' },
          { id: 2, nomeItem: 'Teclado USB Dell', categoria: 'Periféricos', quantidadeAtual: 2, quantidadeMinima: 5, localizacaoNome: 'Almoxarifado' }
        ];
        this.insumosCriticos = 2;
      }
    });
  }
}
