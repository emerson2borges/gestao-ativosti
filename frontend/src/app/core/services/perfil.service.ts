import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PerfilRequest, PerfilResponse, PermissaoResponse } from '../models/perfil.model';

@Injectable({
  providedIn: 'root'
})
export class PerfilService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/perfis';

  listar(): Observable<PerfilResponse[]> {
    return this.http.get<PerfilResponse[]>(this.apiUrl);
  }

  listarPermissoes(): Observable<PermissaoResponse[]> {
    return this.http.get<PermissaoResponse[]>('http://localhost:8080/api/permissoes');
  }

  buscarPorId(id: number): Observable<PerfilResponse> {
    return this.http.get<PerfilResponse>(`${this.apiUrl}/${id}`);
  }

  criar(perfil: PerfilRequest): Observable<PerfilResponse> {
    return this.http.post<PerfilResponse>(this.apiUrl, perfil);
  }

  atualizar(id: number, perfil: PerfilRequest): Observable<PerfilResponse> {
    return this.http.put<PerfilResponse>(`${this.apiUrl}/${id}`, perfil);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
