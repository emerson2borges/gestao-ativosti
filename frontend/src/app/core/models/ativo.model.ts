export interface AtivoResponse {
  id: number;
  patrimonio: string;
  hostnameAtual?: string;
  responsavel?: string;
  status: string;
  tipoId?: number;
  tipoNome?: string;
  localizacaoId?: number;
  localizacaoNome?: string;
  ordemCompraId?: number;
  ordemCompraNumero?: string;
}

export interface AtivoRequest {
  tipoId: number;
  localizacaoId: number;
  ordemCompraId?: number;
  patrimonio: string;
  hostnameAtual?: string;
  responsavel?: string;
  status: string;
  chamadoGlpi?: string;
}
