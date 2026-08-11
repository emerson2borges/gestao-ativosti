export interface HistoricoAtivoResponse {
  id: number;
  ativoId: number;
  ativoPatrimonio?: string;
  dataHora: string;
  tipoEvento: string;
  descricao: string;
  chamadoGlpi?: string;
  usuarioResponsavel?: string;
}

export interface HistoricoInsumoResponse {
  id: number;
  insumoId: number;
  insumoNome?: string;
  dataHora: string;
  tipoMovimentacao: string;
  quantidadeAlterada: number;
  motivo?: string;
  chamadoGlpi?: string;
  usuarioResponsavel?: string;
}
