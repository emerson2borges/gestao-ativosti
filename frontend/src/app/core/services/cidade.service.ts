import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { CidadeRequest, CidadeResponse } from '../models/cidade.model';

@Injectable({
  providedIn: 'root'
})
export class CidadeService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/cidades';

  listar(): Observable<CidadeResponse[]> {
    return this.http.get<CidadeResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<CidadeResponse> {
    return this.http.get<CidadeResponse>(`${this.apiUrl}/${id}`);
  }

  criar(cidade: CidadeRequest): Observable<CidadeResponse> {
    return this.http.post<CidadeResponse>(this.apiUrl, cidade);
  }

  atualizar(id: number, cidade: CidadeRequest): Observable<CidadeResponse> {
    return this.http.put<CidadeResponse>(`${this.apiUrl}/${id}`, cidade);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
