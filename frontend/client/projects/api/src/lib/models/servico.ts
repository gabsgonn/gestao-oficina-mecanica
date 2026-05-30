/* tslint:disable */
/* eslint-disable */
import { ItemServico } from '../models/item-servico';
import { Pessoa } from '../models/pessoa';
export interface Servico {
  cliente?: Pessoa | null;
  criadoEm?: string | null;
  dataFinalizacao?: string | null;
  dataInicio?: string | null;
  descricao?: string | null;
  id?: string | null;
  itens?: Array<ItemServico> | null;
  modificadoEm?: string | null;
  numero?: number | null;
  valor?: number | null;
}
