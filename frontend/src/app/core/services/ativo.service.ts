import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AtivoRequest, AtivoResponse } from '../models/ativo.model';

@Injectable({
  providedIn: 'root'
})
export class AtivoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/ativos';

  listar(): Observable<AtivoResponse[]> {
    return this.http.get<AtivoResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<AtivoResponse> {
    return this.http.get<AtivoResponse>(`${this.apiUrl}/${id}`);
  }

  criar(ativo: AtivoRequest): Observable<AtivoResponse> {
    return this.http.post<AtivoResponse>(this.apiUrl, ativo);
  }

  atualizar(id: number, ativo: AtivoRequest): Observable<AtivoResponse> {
    return this.http.put<AtivoResponse>(`${this.apiUrl}/${id}`, ativo);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  desativar(id: number, chamadoGlpi?: string): Observable<void> {
    const params: Record<string, string> = chamadoGlpi ? { chamadoGlpi } : {};
    return this.http.post<void>(`${this.apiUrl}/${id}/desativar`, null, { params });
  }

  descartar(id: number, chamadoGlpi?: string): Observable<void> {
    const params: Record<string, string> = chamadoGlpi ? { chamadoGlpi } : {};
    return this.http.post<void>(`${this.apiUrl}/${id}/descartar`, null, { params });
  }
}
