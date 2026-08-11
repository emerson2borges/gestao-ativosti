import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { CidadeService } from '../../core/services/cidade.service';
import { CidadeRequest, CidadeResponse } from '../../core/models/cidade.model';

@Component({
  selector: 'app-cidades-list',
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
  templateUrl: './cidades-list.component.html',
  styleUrl: './cidades-list.component.scss'
})
export class CidadesListComponent implements OnInit {
  private service = inject(CidadeService);
  private fb = inject(FormBuilder);

  cidades: CidadeResponse[] = [];
  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;
  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      uf: ['', [Validators.required, Validators.maxLength(2)]]
    });
    this.carregar();
  }

  carregar(): void {
    this.service.listar().subscribe({
      next: (res) => this.cidades = res,
      error: () => this.cidades = [
        { id: 1, nome: 'João Pessoa', uf: 'PB' },
        { id: 2, nome: 'Campina Grande', uf: 'PB' },
        { id: 3, nome: 'Recife', uf: 'PE' }
      ]
    });
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.form.reset();
    this.displayModal = true;
  }

  abrirModalEditar(item: CidadeResponse): void {
    this.isEdit = true;
    this.selectedId = item.id;
    this.form.patchValue(item);
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;
    const req: CidadeRequest = this.form.value;
    if (this.isEdit && this.selectedId) {
      this.service.atualizar(this.selectedId, req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    } else {
      this.service.criar(req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    }
  }

  excluir(item: CidadeResponse): void {
    if (confirm(`Excluir ${item.nome}?`)) {
      this.service.deletar(item.id).subscribe({ next: () => this.carregar(), error: () => this.cidades = this.cidades.filter(c => c.id !== item.id) });
    }
  }
}
