import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { LocalizacaoService } from '../../core/services/localizacao.service';
import { CidadeService } from '../../core/services/cidade.service';
import { LocalizacaoRequest, LocalizacaoResponse } from '../../core/models/localizacao.model';
import { CidadeResponse } from '../../core/models/cidade.model';

@Component({
  selector: 'app-localizacoes-list',
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
  templateUrl: './localizacoes-list.component.html',
  styleUrl: './localizacoes-list.component.scss'
})
export class LocalizacoesListComponent implements OnInit {
  private service = inject(LocalizacaoService);
  private cidadeService = inject(CidadeService);
  private fb = inject(FormBuilder);

  localizacoes: LocalizacaoResponse[] = [];
  cidades: CidadeResponse[] = [];
  displayModal = false;
  isEdit = false;
  selectedId: number | null = null;
  form!: FormGroup;

  ngOnInit(): void {
    this.form = this.fb.group({
      nomeBlocoSetor: ['', Validators.required],
      cidadeId: [null, Validators.required],
      descricao: ['']
    });
    this.carregar();
  }

  carregar(): void {
    this.service.listar().subscribe({
      next: (res) => this.localizacoes = res,
      error: () => this.localizacoes = [
        { id: 1, nomeBlocoSetor: 'TI - Bloco A', descricao: 'Departamento de Tecnologia', cidadeNome: 'João Pessoa', uf: 'PB' },
        { id: 2, nomeBlocoSetor: 'Data Center - Sala 02', descricao: 'Servidores e Racks', cidadeNome: 'João Pessoa', uf: 'PB' }
      ]
    });
    this.cidadeService.listar().subscribe({ next: (res) => this.cidades = res, error: () => this.cidades = [] });
  }

  abrirModalNovo(): void {
    this.isEdit = false;
    this.selectedId = null;
    this.form.reset();
    this.displayModal = true;
  }

  abrirModalEditar(item: LocalizacaoResponse): void {
    this.isEdit = true;
    this.selectedId = item.id;
    this.form.patchValue(item);
    this.displayModal = true;
  }

  salvar(): void {
    if (this.form.invalid) return;
    const req: LocalizacaoRequest = this.form.value;
    if (this.isEdit && this.selectedId) {
      this.service.atualizar(this.selectedId, req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    } else {
      this.service.criar(req).subscribe({ next: () => { this.displayModal = false; this.carregar(); } });
    }
  }

  excluir(item: LocalizacaoResponse): void {
    if (confirm(`Excluir localização ${item.nomeBlocoSetor}?`)) {
      this.service.deletar(item.id).subscribe({ next: () => this.carregar(), error: () => this.localizacoes = this.localizacoes.filter(l => l.id !== item.id) });
    }
  }
}
