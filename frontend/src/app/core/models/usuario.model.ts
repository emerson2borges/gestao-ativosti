export interface UsuarioResponse {
  id: number;
  nome: string;
  matricula: string;
  email: string;
  perfilId?: number;
  perfilNome?: string;
}

export interface UsuarioRequest {
  nome: string;
  matricula: string;
  email: string;
  senha?: string;
  perfilId: number;
}
