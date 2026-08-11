export interface EstoqueInsumoResponse {
  id: number;
  nomeItem: string;
  categoria?: string;
  quantidadeAtual: number;
  quantidadeMinima: number;
  localizacaoId?: number;
  localizacaoNome?: string;
}

export interface EstoqueInsumoRequest {
  nomeItem: string;
  categoria?: string;
  quantidadeAtual: number;
  quantidadeMinima: number;
  localizacaoId?: number;
}
