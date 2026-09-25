export interface OrdemCompraResponse {
  id: number;
  numeroOrdem: string;
  fornecedor?: string;
  dataCompra?: string;
  valorTotal?: number;
  descricao?: string;
}

export interface OrdemCompraRequest {
  numeroOrdem: string;
  fornecedor?: string;
  dataCompra?: string;
  valorTotal?: number;
  descricao?: string;
}
