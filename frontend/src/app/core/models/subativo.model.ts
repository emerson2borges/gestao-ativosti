export interface SubativoInternoResponse {
  id: number;
  tipo: string;
  fabricante?: string;
  modelo?: string;
  numeroSerie?: string;
  especificacoesTecnicas?: string;
  status: string;
  ativoId?: number;
  ativoPatrimonio?: string;
}

export interface SubativoInternoRequest {
  tipo: string;
  fabricante?: string;
  modelo?: string;
  numeroSerie?: string;
  especificacoesTecnicas?: string;
  status: string;
  ativoId?: number;
}
