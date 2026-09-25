import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TableModule } from 'primeng/table';
import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { HistoricoService } from '../../core/services/historico.service';
import { HistoricoAtivoResponse, HistoricoInsumoResponse } from '../../core/models/historico.model';

@Component({
  selector: 'app-historico-list',
  standalone: true,
  imports: [
    CommonModule,
    TableModule,
    PageHeaderComponent
  ],
  templateUrl: './historico-list.component.html',
  styleUrl: './historico-list.component.scss'
})
export class HistoricoListComponent implements OnInit {
  private service = inject(HistoricoService);

  activeTab: 'ativos' | 'insumos' = 'ativos';
  historicoAtivos: HistoricoAtivoResponse[] = [];
  historicoInsumos: HistoricoInsumoResponse[] = [];

  ngOnInit(): void {
    this.carregar();
  }

  carregar(): void {
    this.service.listarHistoricoAtivos().subscribe({
      next: (res) => this.historicoAtivos = res,
      error: () => this.historicoAtivos = [
        { id: 1, ativoId: 1, ativoPatrimonio: 'PAT-2026-001', dataHora: '2026-02-09T14:30:00', tipoEvento: 'CRIAÇÃO', descricao: 'Ativo cadastrado no sistema', chamadoGlpi: 'GLPI-4509', usuarioResponsavel: 'Técnico TI' },
        { id: 2, ativoId: 1, ativoPatrimonio: 'PAT-2026-001', dataHora: '2026-02-09T15:00:00', tipoEvento: 'INSTALAÇÃO_SUBATIVO', descricao: 'Instalada Memória RAM Corsair 16GB', chamadoGlpi: 'GLPI-4512', usuarioResponsavel: 'Técnico TI' }
      ]
    });

    this.service.listarHistoricoInsumos().subscribe({
      next: (res) => this.historicoInsumos = res,
      error: () => this.historicoInsumos = [
        { id: 1, insumoId: 1, insumoNome: 'Cabo Patch Cord Cat6 2m', dataHora: '2026-02-08T10:15:00', tipoMovimentacao: 'SAIDA', quantidadeAlterada: 2, motivo: 'Instalação de ponto de rede', chamadoGlpi: 'GLPI-4480' }
      ]
    });
  }
}
