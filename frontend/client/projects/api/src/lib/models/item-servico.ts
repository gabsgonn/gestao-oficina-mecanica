/* tslint:disable */
/* eslint-disable */
import { Peca } from '../models/peca';
import { TipoServico } from '../models/tipo-servico';
export interface ItemServico {
  descricao?: string | null;
  id?: string | null;
  peca?: Peca | null;
  quantidade?: number | null;
  tipo?: string | null;
  tipoServico?: TipoServico | null;
}
