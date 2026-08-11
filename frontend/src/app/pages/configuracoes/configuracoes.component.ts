import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TableModule } from 'primeng/table';
import { ButtonModule } from 'primeng/button';
import { DialogModule } from 'primeng/dialog';
import { InputTextModule } from 'primeng/inputtext';
import { SelectModule } from 'primeng/select';

import { PageHeaderComponent } from '../../shared/components/page-header/page-header.component';
import { UsuarioService } from '../../core/services/usuario.service';
import { PerfilService } from '../../core/services/perfil.service';
import { UsuarioRequest, UsuarioResponse } from '../../core/models/usuario.model';
import { PerfilRequest, PerfilResponse } from '../../core/models/perfil.model';

@Component({
  selector: 'app-configuracoes',
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
  templateUrl: './configuracoes.component.html',
  styleUrl: './configuracoes.component.scss'
})
export class ConfiguracoesComponent implements OnInit {
  private usuarioService = inject(UsuarioService);
  private perfilService = inject(PerfilService);
  private fb = inject(FormBuilder);

  activeTab: 'usuarios' | 'perfis' = 'usuarios';

  usuarios: UsuarioResponse[] = [];
  perfis: PerfilResponse[] = [];

  displayModalUsuario = false;
  isEditUsuario = false;
  selectedUsuarioId: number | null = null;
  formUsuario!: FormGroup;

  displayModalPerfil = false;
  isEditPerfil = false;
  selectedPerfilId: number | null = null;
  formPerfil!: FormGroup;

  ngOnInit(): void {
    this.formUsuario = this.fb.group({
      nome: ['', Validators.required],
      matricula: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      senha: [''],
      perfilId: [null, Validators.required]
    });

    this.formPerfil = this.fb.group({
      nome: ['', Validators.required]
    });

    this.carregar();
  }

  carregar(): void {
    this.usuarioService.listar().subscribe({
      next: (res) => this.usuarios = res,
      error: () => this.usuarios = [
        { id: 1, nome: 'Administrador Geral', matricula: 'ADM-001', email: 'admin@empresa.com', perfilId: 1, perfilNome: 'Administrador' },
        { id: 2, nome: 'Técnico de Suporte', matricula: 'TEC-002', email: 'suporte@empresa.com', perfilId: 2, perfilNome: 'Técnico' }
      ]
    });

    this.perfilService.listar().subscribe({
      next: (res) => this.perfis = res,
      error: () => this.perfis = [
        { id: 1, nome: 'Administrador', permissoes: [{ id: 1, chave: 'TODAS', descricao: 'Acesso Total ao Sistema' }] },
        { id: 2, nome: 'Técnico TI', permissoes: [{ id: 2, chave: 'ATIVOS_EDIT', descricao: 'Editar Ativos e Insumos' }] }
      ]
    });
  }

  // --- Usuários ---
  abrirModalNovoUsuario(): void {
    this.isEditUsuario = false;
    this.selectedUsuarioId = null;
    this.formUsuario.reset();
    this.displayModalUsuario = true;
  }

  abrirModalEditarUsuario(user: UsuarioResponse): void {
    this.isEditUsuario = true;
    this.selectedUsuarioId = user.id;
    this.formUsuario.patchValue(user);
    this.displayModalUsuario = true;
  }

  salvarUsuario(): void {
    if (this.formUsuario.invalid) return;
    const req: UsuarioRequest = this.formUsuario.value;
    if (this.isEditUsuario && this.selectedUsuarioId) {
      this.usuarioService.atualizar(this.selectedUsuarioId, req).subscribe({
        next: () => { this.displayModalUsuario = false; this.carregar(); }
      });
    } else {
      this.usuarioService.criar(req).subscribe({
        next: () => { this.displayModalUsuario = false; this.carregar(); }
      });
    }
  }

  excluirUsuario(user: UsuarioResponse): void {
    if (confirm(`Remover usuário ${user.nome}?`)) {
      this.usuarioService.deletar(user.id).subscribe({
        next: () => this.carregar(),
        error: () => this.usuarios = this.usuarios.filter(u => u.id !== user.id)
      });
    }
  }

  // --- Perfis ---
  abrirModalNovoPerfil(): void {
    this.isEditPerfil = false;
    this.selectedPerfilId = null;
    this.formPerfil.reset();
    this.displayModalPerfil = true;
  }

  abrirModalEditarPerfil(perfil: PerfilResponse): void {
    this.isEditPerfil = true;
    this.selectedPerfilId = perfil.id;
    this.formPerfil.patchValue(perfil);
    this.displayModalPerfil = true;
  }

  salvarPerfil(): void {
    if (this.formPerfil.invalid) return;
    const req: PerfilRequest = this.formPerfil.value;
    if (this.isEditPerfil && this.selectedPerfilId) {
      this.perfilService.atualizar(this.selectedPerfilId, req).subscribe({
        next: () => { this.displayModalPerfil = false; this.carregar(); }
      });
    } else {
      this.perfilService.criar(req).subscribe({
        next: () => { this.displayModalPerfil = false; this.carregar(); }
      });
    }
  }

  excluirPerfil(perfil: PerfilResponse): void {
    if (confirm(`Remover perfil ${perfil.nome}?`)) {
      this.perfilService.deletar(perfil.id).subscribe({
        next: () => this.carregar(),
        error: () => this.perfis = this.perfis.filter(p => p.id !== perfil.id)
      });
    }
  }
}
