/* tslint:disable */
/* eslint-disable */
import { Cliente } from '../models/cliente';
import { ItemServico } from '../models/item-servico';
import { Peca } from '../models/peca';
import { Pessoa } from '../models/pessoa';
import { Servico } from '../models/servico';
import { TipoServico } from '../models/tipo-servico';
import { Veiculo } from '../models/veiculo';
export interface Resultado {
  clientes?: Array<Cliente> | null;
  itemServico?: Array<ItemServico> | null;
  pecas?: Array<Peca> | null;
  pessoas?: Array<Pessoa> | null;
  servicos?: Array<Servico> | null;
  tipoServicos?: Array<TipoServico> | null;
  veiculos?: Array<Veiculo> | null;
}
