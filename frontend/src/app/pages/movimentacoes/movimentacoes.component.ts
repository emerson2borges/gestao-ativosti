import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';

export interface Movimentacao {
  id: number;
  dataHora: string;
  tipo: 'TRANSFERENCIA' | 'ENTRADA' | 'SAIDA';
  itemNome: string;
  origem?: string;
  destino?: string;
  quantidade: number;
  glpi?: string;
  usuarioResponsavel: string;
  observacao?: string;
}

@Component({
  selector: 'app-movimentacoes',
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
    PageHeaderComponent
  ],
  templateUrl: './movimentacoes.component.html',
  styleUrl: './movimentacoes.component.scss'
})
export class MovimentacoesComponent implements OnInit {
  private fb = inject(FormBuilder);

  movimentacoes: Movimentacao[] = [];
  displayModal = false;
  form!: FormGroup;

  tiposOperacao = [
    { label: 'Transferência de Ativo', value: 'TRANSFERENCIA' },
    { label: 'Entrada de Insumos', value: 'ENTRADA' },
    { label: 'Saída de Insumos', value: 'SAIDA' }
  ];

  ngOnInit(): void {
    this.form = this.fb.group({
      tipo: ['TRANSFERENCIA', Validators.required],
      itemNome: ['', Validators.required],
      origem: [''],
      destino: [''],
      glpi: [''],
      quantidade: [1, [Validators.required, Validators.min(1)]],
      observacao: ['']
    });

    this.carregarMock();
  }

  carregarMock(): void {
    this.movimentacoes = [
      { id: 1, dataHora: '2026-02-10T11:00:00', tipo: 'TRANSFERENCIA', itemNome: 'PAT-2026-001 (Notebook Dell)', origem: 'TI - Bloco A', destino: 'Bloco B - Sala 102', quantidade: 1, glpi: 'GLPI-4900', usuarioResponsavel: 'Carlos Silva' },
      { id: 2, dataHora: '2026-02-09T16:20:00', tipo: 'SAIDA', itemNome: 'Cabo Patch Cord Cat6 2m', origem: 'Almoxarifado TI', destino: 'Data Center', quantidade: 5, glpi: 'GLPI-4850', usuarioResponsavel: 'Ana Lima' },
      { id: 3, dataHora: '2026-02-08T09:15:00', tipo: 'ENTRADA', itemNome: 'Mouse Óptico USB Dell', origem: 'Fornecedor Tech', destino: 'Almoxarifado TI', quantidade: 20, glpi: 'OC-2026/01', usuarioResponsavel: 'Carlos Silva' }
    ];
  }

  abrirModalNova(): void {
    this.form.reset({ tipo: 'TRANSFERENCIA', quantidade: 1 });
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;

    const nova: Movimentacao = {
      id: Date.now(),
      dataHora: new Date().toISOString(),
      ...this.form.value,
      usuarioResponsavel: 'Usuário Logado'
    };

    this.movimentacoes = [nova, ...this.movimentacoes];
    this.displayModal = false;
  }
}
