export interface LocalizacaoResponse {
  id: number;
  nomeBlocoSetor: string;
  descricao?: string;
  cidadeId?: number;
  cidadeNome?: string;
  uf?: string;
}

export interface LocalizacaoRequest {
  nomeBlocoSetor: string;
  descricao?: string;
  cidadeId: number;
}
