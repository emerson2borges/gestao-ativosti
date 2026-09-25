export interface TipoAtivoResponse {
  id: number;
  nome: string;
  descricao?: string;
}

export interface TipoAtivoRequest {
  nome: string;
  descricao?: string;
}
