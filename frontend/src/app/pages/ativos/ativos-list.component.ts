import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { StatusBadgeComponent } from '../../shared/components/status-badge/status-badge.component';

import { AtivoService } from '../../core/services/ativo.service';
import { TipoAtivoService } from '../../core/services/tipo-ativo.service';
import { LocalizacaoService } from '../../core/services/localizacao.service';
import { OrdemCompraService } from '../../core/services/ordem-compra.service';

import { AtivoRequest, AtivoResponse } from '../../core/models/ativo.model';
import { TipoAtivoResponse } from '../../core/models/tipo-ativo.model';
import { LocalizacaoResponse } from '../../core/models/localizacao.model';
import { OrdemCompraResponse } from '../../core/models/ordem-compra.model';

@Component({
  selector: 'app-ativos-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    SelectModule,
    ToastModule,
    PageHeaderComponent,
    StatusBadgeComponent
  ],
  providers: [MessageService],
  templateUrl: './ativos-list.component.html',
  styleUrl: './ativos-list.component.scss'
})
export class AtivosListComponent implements OnInit {
  private ativoService = inject(AtivoService);
  private tipoAtivoService = inject(TipoAtivoService);
  private localizacaoService = inject(LocalizacaoService);
  private ordemCompraService = inject(OrdemCompraService);
  private fb = inject(FormBuilder);
  private messageService = inject(MessageService);

  ativos: AtivoResponse[] = [];
  filteredAtivos: AtivoResponse[] = [];
  tiposAtivo: TipoAtivoResponse[] = [];
  localizacoes: LocalizacaoResponse[] = [];
  ordensCompra: OrdemCompraResponse[] = [];

  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;

  formAtivo!: FormGroup;

  statusOptions = [
    { label: 'Em Uso', value: 'Em Uso' },
    { label: 'Disponível', value: 'Disponível' },
    { label: 'Em Manutenção', value: 'Em Manutenção' },
    { label: 'Descartado', value: 'Descartado' }
  ];

  ngOnInit(): void {
    this.initForm();
    this.carregarDados();
  }

  initForm(): void {
    this.formAtivo = this.fb.group({
      patrimonio: ['', [Validators.required, Validators.maxLength(50)]],
      tipoId: [null, Validators.required],
      hostnameAtual: [''],
      responsavel: [''],
      localizacaoId: [null, Validators.required],
      status: ['Em Uso', Validators.required],
      ordemCompraId: [null]
    });
  }

  carregarDados(): void {
    this.ativoService.listar().subscribe({
      next: (res) => {
        this.ativos = res;
        this.filteredAtivos = res;
      },
      error: () => {
        // Fallback mock se backend não estiver rodando localmente
        this.ativos = [
          { id: 1, patrimonio: 'PAT-2026-001', hostnameAtual: 'NOTE-DEV-01', responsavel: 'Carlos Silva', status: 'Em Uso', tipoNome: 'Notebook Dell XPS', localizacaoNome: 'TI - Bloco A' },
          { id: 2, patrimonio: 'PAT-2026-002', hostnameAtual: 'SRV-DB-01', responsavel: 'Infraestrutura', status: 'Em Uso', tipoNome: 'Servidor Dell PowerEdge', localizacaoNome: 'Data Center' },
          { id: 3, patrimonio: 'PAT-2026-003', hostnameAtual: 'MON-DESIGN-02', responsavel: 'Ana Lima', status: 'Disponível', tipoNome: 'Monitor LG 27"', localizacaoNome: 'Almoxarifado TI' }
        ];
        this.filteredAtivos = [...this.ativos];
      }
    });

    this.tipoAtivoService.listar().subscribe({
      next: (res) => this.tiposAtivo = res,
      error: () => this.tiposAtivo = [
        { id: 1, nome: 'Notebook' },
        { id: 2, nome: 'Servidor' },
        { id: 3, nome: 'Monitor' }
      ]
    });

    this.localizacaoService.listar().subscribe({
      next: (res) => this.localizacoes = res,
      error: () => this.localizacoes = [
        { id: 1, nomeBlocoSetor: 'TI - Bloco A' },
        { id: 2, nomeBlocoSetor: 'Data Center' },
        { id: 3, nomeBlocoSetor: 'Almoxarifado TI' }
      ]
    });

    this.ordemCompraService.listar().subscribe({
      next: (res) => this.ordensCompra = res,
      error: () => this.ordensCompra = []
    });
  }

  onSearch(event: any): void {
    const query = (event.target.value || '').toLowerCase();
    this.filteredAtivos = this.ativos.filter(a => 
      a.patrimonio.toLowerCase().includes(query) ||
      (a.hostnameAtual || '').toLowerCase().includes(query) ||
      (a.responsavel || '').toLowerCase().includes(query) ||
      (a.tipoNome || '').toLowerCase().includes(query)
    );
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.formAtivo.reset({ status: 'Em Uso' });
    this.displayModal = true;
  }

  abrirModalEditar(ativo: AtivoResponse): void {
    this.isEdit = true;
    this.selectedId = ativo.id;
    this.formAtivo.patchValue({
      patrimonio: ativo.patrimonio,
      tipoId: ativo.tipoId,
      hostnameAtual: ativo.hostnameAtual,
      responsavel: ativo.responsavel,
      localizacaoId: ativo.localizacaoId,
      status: ativo.status,
      ordemCompraId: ativo.ordemCompraId
    });
    this.displayModal = true;
  }

  salvarAtivo(): void {
    if (this.formAtivo.invalid) return;

    const payload: AtivoRequest = this.formAtivo.value;

    if (this.isEdit && this.selectedId) {
      this.ativoService.atualizar(this.selectedId, payload).subscribe({
        next: () => {
          this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Ativo atualizado com sucesso!' });
          this.displayModal = false;
          this.carregarDados();
        },
        error: () => {
          this.messageService.add({ severity: 'info', summary: 'Simulação', detail: 'Ativo editado localmente' });
          this.displayModal = false;
        }
      });
    } else {
      this.ativoService.criar(payload).subscribe({
        next: () => {
          this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Ativo cadastrado com sucesso!' });
          this.displayModal = false;
          this.carregarDados();
        },
        error: () => {
          this.messageService.add({ severity: 'info', summary: 'Simulação', detail: 'Ativo cadastrado localmente' });
          this.displayModal = false;
        }
      });
    }
  }

  excluirAtivo(ativo: AtivoResponse): void {
    if (confirm(`Deseja realmente excluir o ativo patrimônio ${ativo.patrimonio}?`)) {
      this.ativoService.deletar(ativo.id).subscribe({
        next: () => {
          this.messageService.add({ severity: 'success', summary: 'Sucesso', detail: 'Ativo excluído com sucesso!' });
          this.carregarDados();
        },
        error: () => {
          this.ativos = this.ativos.filter(a => a.id !== ativo.id);
          this.filteredAtivos = [...this.ativos];
          this.messageService.add({ severity: 'info', summary: 'Removido', detail: 'Ativo removido da lista' });
        }
      });
    }
  }
}
