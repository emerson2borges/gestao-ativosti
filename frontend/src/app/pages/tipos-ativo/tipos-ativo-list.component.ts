import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { ToastModule } from 'primeng/toast';
import { MessageService } from 'primeng/api';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { TipoAtivoService } from '../../core/services/tipo-ativo.service';
import { TipoAtivoRequest, TipoAtivoResponse } from '../../core/models/tipo-ativo.model';

@Component({
  selector: 'app-tipos-ativo-list',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    TableModule,
    ButtonModule,
    DialogModule,
    InputTextModule,
    ToastModule,
    PageHeaderComponent
  ],
  providers: [MessageService],
  templateUrl: './tipos-ativo-list.component.html',
  styleUrl: './tipos-ativo-list.component.scss'
})
export class TiposAtivoListComponent implements OnInit {
  private service = inject(TipoAtivoService);
  private fb = inject(FormBuilder);
  private messageService = inject(MessageService);

  tipos: TipoAtivoResponse[] = [];
  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;
  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      nome: ['', Validators.required],
      descricao: ['']
    });
    this.carregar();
  }

  carregar(): void {
    this.service.listar().subscribe({
      next: (res) => this.tipos = res,
      error: () => this.tipos = [
        { id: 1, nome: 'Notebook', descricao: 'Laptops corporativos' },
        { id: 2, nome: 'Servidor', descricao: 'Racks e servidores fisicos' },
        { id: 3, nome: 'Monitor', descricao: 'Telas e monitores adicionais' }
      ]
    });
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.form.reset();
    this.displayModal = true;
  }

  abrirModalEditar(tipo: TipoAtivoResponse): void {
    this.isEdit = true;
    this.selectedId = tipo.id;
    this.form.patchValue(tipo);
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;
    const req: TipoAtivoRequest = this.form.value;
    if (this.isEdit && this.selectedId) {
      this.service.atualizar(this.selectedId, req).subscribe({
        next: () => { this.displayModal = false; this.carregar(); }
      });
    } else {
      this.service.criar(req).subscribe({
        next: () => { this.displayModal = false; this.carregar(); }
      });
    }
  }

  excluir(tipo: TipoAtivoResponse): void {
    if (confirm(`Excluir ${tipo.nome}?`)) {
      this.service.deletar(tipo.id).subscribe({
        next: () => this.carregar(),
        error: () => this.tipos = this.tipos.filter(t => t.id !== tipo.id)
      });
    }
  }
}
