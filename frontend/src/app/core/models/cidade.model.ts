export interface CidadeResponse {
  id: number;
  nome: string;
  uf: string;
}

export interface CidadeRequest {
  nome: string;
  uf: string;
}
