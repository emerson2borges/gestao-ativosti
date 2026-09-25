import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HistoricoAtivoResponse, HistoricoInsumoResponse } from '../models/historico.model';

@Injectable({
  providedIn: 'root'
})
export class HistoricoService {
  private http = inject(HttpClient);
  private apiUrlAtivos = 'http://localhost:8080/api/historico-ativos';
  private apiUrlInsumos = 'http://localhost:8080/api/historico-insumos';

  listarHistoricoAtivos(): Observable<HistoricoAtivoResponse[]> {
    return this.http.get<HistoricoAtivoResponse[]>(this.apiUrlAtivos);
  }

  buscarHistoricoPorAtivo(ativoId: number): Observable<HistoricoAtivoResponse[]> {
    return this.http.get<HistoricoAtivoResponse[]>(`${this.apiUrlAtivos}/ativo/${ativoId}`);
  }

  listarHistoricoInsumos(): Observable<HistoricoInsumoResponse[]> {
    return this.http.get<HistoricoInsumoResponse[]>(this.apiUrlInsumos);
  }
}
