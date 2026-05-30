/* tslint:disable */
/* eslint-disable */
import { Pessoa } from '../models/pessoa';
import { Servico } from '../models/servico';
export interface Cliente {
  id?: string | null;
  pessoa?: Pessoa | null;
  pontos?: number | null;
  sevicos?: Servico | null;
}
