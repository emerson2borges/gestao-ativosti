import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';
import { ToastModule } from 'primeng/toast';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { StatusBadgeComponent } from '../../shared/components/status-badge/status-badge.component';
import { SubativoService } from '../../core/services/subativo.service';
import { AtivoService } from '../../core/services/ativo.service';
import { SubativoInternoRequest, SubativoInternoResponse } from '../../core/models/subativo.model';
import { AtivoResponse } from '../../core/models/ativo.model';

@Component({
  selector: 'app-subativos-list',
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
  templateUrl: './subativos-list.component.html',
  styleUrl: './subativos-list.component.scss'
})
export class SubativosListComponent implements OnInit {
  private service = inject(SubativoService);
  private ativoService = inject(AtivoService);
  private fb = inject(FormBuilder);

  subativos: SubativoInternoResponse[] = [];
  ativos: AtivoResponse[] = [];
  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;
  form!: FormGroup;

  statusOpts = [
    { label: 'Em Estoque', value: 'Disponível' },
    { label: 'Instalado', value: 'Em Uso' },
    { label: 'Danificado', value: 'Descartado' }
  ];

  ngOnInit(): void {
    this.form = this.fb.group({
      tipo: ['', Validators.required],
      fabricante: [''],
      modelo: [''],
      numeroSerie: [''],
      especificacoesTecnicas: [''],
      status: ['Disponível', Validators.required],
      ativoId: [null]
    });
    this.carregar();
  }

  carregar(): void {
    this.service.listar().subscribe({
      next: (res) => this.subativos = res,
      error: () => this.subativos = [
        { id: 1, tipo: 'Memória RAM', fabricante: 'Corsair', modelo: 'Vengeance LPX 16GB', numeroSerie: 'SN-RAM-001', especificacoesTecnicas: 'DDR4 3200MHz', status: 'Em Uso', ativoPatrimonio: 'PAT-2026-001' },
        { id: 2, tipo: 'SSD M.2 NVMe', fabricante: 'Samsung', modelo: '980 PRO 1TB', numeroSerie: 'SN-SSD-992', especificacoesTecnicas: 'PCIe 4.0 7000MB/s', status: 'Disponível' }
      ]
    });
    this.ativoService.listar().subscribe({ next: (res) => this.ativos = res, error: () => this.ativos = [] });
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.form.reset({ status: 'Disponível' });
    this.displayModal = true;
  }

  abrirModalEditar(item: SubativoInternoResponse): void {
    this.isEdit = true;
    this.selectedId = item.id;
    this.form.patchValue(item);
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;
    const req: SubativoInternoRequest = this.form.value;
    if (this.isEdit && this.selectedId) {
      this.service.atualizar(this.selectedId, req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    } else {
      this.service.criar(req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    }
  }

  excluir(item: SubativoInternoResponse): void {
    if (confirm(`Remover subativo ${item.tipo}?`)) {
      this.service.deletar(item.id).subscribe({ next: () => this.carregar(), error: () => this.subativos = this.subativos.filter(s => s.id !== item.id) });
    }
  }
}
