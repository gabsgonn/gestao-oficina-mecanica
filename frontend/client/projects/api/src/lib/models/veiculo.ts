/* tslint:disable */
/* eslint-disable */
import { Pessoa } from '../models/pessoa';
export interface Veiculo {
  placa?: string | null;
  proprietario?: Pessoa | null;
}
