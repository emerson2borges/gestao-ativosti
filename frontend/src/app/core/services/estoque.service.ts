import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { EstoqueInsumoRequest, EstoqueInsumoResponse } from '../models/estoque-insumo.model';

@Injectable({
  providedIn: 'root'
})
export class EstoqueService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/estoque-insumos';

  listar(): Observable<EstoqueInsumoResponse[]> {
    return this.http.get<EstoqueInsumoResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<EstoqueInsumoResponse> {
    return this.http.get<EstoqueInsumoResponse>(`${this.apiUrl}/${id}`);
  }

  criar(item: EstoqueInsumoRequest): Observable<EstoqueInsumoResponse> {
    return this.http.post<EstoqueInsumoResponse>(this.apiUrl, item);
  }

  atualizar(id: number, item: EstoqueInsumoRequest): Observable<EstoqueInsumoResponse> {
    return this.http.put<EstoqueInsumoResponse>(`${this.apiUrl}/${id}`, item);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
