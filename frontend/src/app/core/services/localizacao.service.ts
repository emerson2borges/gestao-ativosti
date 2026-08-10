import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { LocalizacaoRequest, LocalizacaoResponse } from '../models/localizacao.model';

@Injectable({
  providedIn: 'root'
})
export class LocalizacaoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/localizacoes';

  listar(): Observable<LocalizacaoResponse[]> {
    return this.http.get<LocalizacaoResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<LocalizacaoResponse> {
    return this.http.get<LocalizacaoResponse>(`${this.apiUrl}/${id}`);
  }

  criar(loc: LocalizacaoRequest): Observable<LocalizacaoResponse> {
    return this.http.post<LocalizacaoResponse>(this.apiUrl, loc);
  }

  atualizar(id: number, loc: LocalizacaoRequest): Observable<LocalizacaoResponse> {
    return this.http.put<LocalizacaoResponse>(`${this.apiUrl}/${id}`, loc);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
