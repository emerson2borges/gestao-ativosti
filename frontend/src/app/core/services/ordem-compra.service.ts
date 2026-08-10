import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { OrdemCompraRequest, OrdemCompraResponse } from '../models/ordem-compra.model';

@Injectable({
  providedIn: 'root'
})
export class OrdemCompraService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/ordens-compra';

  listar(): Observable<OrdemCompraResponse[]> {
    return this.http.get<OrdemCompraResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<OrdemCompraResponse> {
    return this.http.get<OrdemCompraResponse>(`${this.apiUrl}/${id}`);
  }

  criar(ordem: OrdemCompraRequest): Observable<OrdemCompraResponse> {
    return this.http.post<OrdemCompraResponse>(this.apiUrl, ordem);
  }

  atualizar(id: number, ordem: OrdemCompraRequest): Observable<OrdemCompraResponse> {
    return this.http.put<OrdemCompraResponse>(`${this.apiUrl}/${id}`, ordem);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
