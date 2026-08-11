import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { OrdemCompraService } from '../../core/services/ordem-compra.service';
import { OrdemCompraRequest, OrdemCompraResponse } from '../../core/models/ordem-compra.model';

@Component({
  selector: 'app-ordens-compra-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    PageHeaderComponent
  ],
  templateUrl: './ordens-compra-list.component.html',
  styleUrl: './ordens-compra-list.component.scss'
})
export class OrdensCompraListComponent implements OnInit {
  private service = inject(OrdemCompraService);
  private fb = inject(FormBuilder);

  ordens: OrdemCompraResponse[] = [];
  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;
  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      numeroOrdem: ['', Validators.required],
      fornecedor: [''],
      dataCompra: [''],
      valorTotal: [null],
      descricao: ['']
    });
    this.carregar();
  }

  carregar(): void {
    this.service.listar().subscribe({
      next: (res) => this.ordens = res,
      error: () => this.ordens = [
        { id: 1, numeroOrdem: 'OC-2026/001', fornecedor: 'Dell Computadores', dataCompra: '2026-01-15', valorTotal: 45000.00, descricao: 'Lote de 5 Notebooks Latitude' },
        { id: 2, numeroOrdem: 'OC-2026-002', fornecedor: 'Kabum Comércio', dataCompra: '2026-02-02', valorTotal: 8200.50, descricao: 'Monitores e Periféricos' }
      ]
    });
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.form.reset();
    this.displayModal = true;
  }

  abrirModalEditar(item: OrdemCompraResponse): void {
    this.isEdit = true;
    this.selectedId = item.id;
    this.form.patchValue(item);
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;
    const req: OrdemCompraRequest = this.form.value;
    if (this.isEdit && this.selectedId) {
      this.service.atualizar(this.selectedId, req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    } else {
      this.service.criar(req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    }
  }

  excluir(item: OrdemCompraResponse): void {
    if (confirm(`Excluir ordem ${item.numeroOrdem}?`)) {
      this.service.deletar(item.id).subscribe({ next: () => this.carregar(), error: () => this.ordens = this.ordens.filter(o => o.id !== item.id) });
    }
  }
}
