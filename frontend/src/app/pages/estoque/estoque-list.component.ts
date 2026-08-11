import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { EstoqueService } from '../../core/services/estoque.service';
import { LocalizacaoService } from '../../core/services/localizacao.service';
import { EstoqueInsumoRequest, EstoqueInsumoResponse } from '../../core/models/estoque-insumo.model';
import { LocalizacaoResponse } from '../../core/models/localizacao.model';

@Component({
  selector: 'app-estoque-list',
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
  templateUrl: './estoque-list.component.html',
  styleUrl: './estoque-list.component.scss'
})
export class EstoqueListComponent implements OnInit {
  private service = inject(EstoqueService);
  private locService = inject(LocalizacaoService);
  private fb = inject(FormBuilder);

  estoque: EstoqueInsumoResponse[] = [];
  localizacoes: LocalizacaoResponse[] = [];
  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;
  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      nomeItem: ['', Validators.required],
      categoria: [''],
      quantidadeAtual: [0, [Validators.required, Validators.min(0)]],
      quantidadeMinima: [5, [Validators.required, Validators.min(0)]],
      localizacaoId: [null]
    });
    this.carregar();
  }

  carregar(): void {
    this.service.listar().subscribe({
      next: (res) => this.estoque = res,
      error: () => this.estoque = [
        { id: 1, nomeItem: 'Cabo Patch Cord Cat6 2m', categoria: 'Redes', quantidadeAtual: 3, quantidadeMinima: 10, localizacaoNome: 'Almoxarifado Central' },
        { id: 2, nomeItem: 'Mouse Óptico USB Dell', categoria: 'Periféricos', quantidadeAtual: 15, quantidadeMinima: 5, localizacaoNome: 'TI - Bloco A' }
      ]
    });
    this.locService.listar().subscribe({ next: (res) => this.localizacoes = res, error: () => this.localizacoes = [] });
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.form.reset({ quantidadeAtual: 0, quantidadeMinima: 5 });
    this.displayModal = true;
  }

  abrirModalEditar(item: EstoqueInsumoResponse): void {
    this.isEdit = true;
    this.selectedId = item.id;
    this.form.patchValue(item);
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;
    const req: EstoqueInsumoRequest = this.form.value;
    if (this.isEdit && this.selectedId) {
      this.service.atualizar(this.selectedId, req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    } else {
      this.service.criar(req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    }
  }

  excluir(item: EstoqueInsumoResponse): void {
    if (confirm(`Excluir item ${item.nomeItem}?`)) {
      this.service.deletar(item.id).subscribe({ next: () => this.carregar(), error: () => this.estoque = this.estoque.filter(e => e.id !== item.id) });
    }
  }
}
