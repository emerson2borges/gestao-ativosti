export interface PerfilResponse {
  id: number;
  nome: string;
  permissoes?: PermissaoResponse[];
}

export interface PerfilRequest {
  nome: string;
  permissaoIds?: number[];
}

export interface PermissaoResponse {
  id: number;
  chave: string;
  descricao: string;
}
