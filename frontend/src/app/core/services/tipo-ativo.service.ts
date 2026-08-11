import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoAtivoRequest, TipoAtivoResponse } from '../models/tipo-ativo.model';

@Injectable({
  providedIn: 'root'
})
export class TipoAtivoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/tipos-ativos';

  listar(): Observable<TipoAtivoResponse[]> {
    return this.http.get<TipoAtivoResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<TipoAtivoResponse> {
    return this.http.get<TipoAtivoResponse>(`${this.apiUrl}/${id}`);
  }

  criar(tipo: TipoAtivoRequest): Observable<TipoAtivoResponse> {
    return this.http.post<TipoAtivoResponse>(this.apiUrl, tipo);
  }

  atualizar(id: number, tipo: TipoAtivoRequest): Observable<TipoAtivoResponse> {
    return this.http.put<TipoAtivoResponse>(`${this.apiUrl}/${id}`, tipo);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
