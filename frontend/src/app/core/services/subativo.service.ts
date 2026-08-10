import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { SubativoInternoRequest, SubativoInternoResponse } from '../models/subativo.model';

@Injectable({
  providedIn: 'root'
})
export class SubativoService {
  private http = inject(HttpClient);
  private apiUrl = 'http://localhost:8080/api/subativos';

  listar(): Observable<SubativoInternoResponse[]> {
    return this.http.get<SubativoInternoResponse[]>(this.apiUrl);
  }

  buscarPorId(id: number): Observable<SubativoInternoResponse> {
    return this.http.get<SubativoInternoResponse>(`${this.apiUrl}/${id}`);
  }

  criar(subativo: SubativoInternoRequest): Observable<SubativoInternoResponse> {
    return this.http.post<SubativoInternoResponse>(this.apiUrl, subativo);
  }

  atualizar(id: number, subativo: SubativoInternoRequest): Observable<SubativoInternoResponse> {
    return this.http.put<SubativoInternoResponse>(`${this.apiUrl}/${id}`, subativo);
  }

  deletar(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
