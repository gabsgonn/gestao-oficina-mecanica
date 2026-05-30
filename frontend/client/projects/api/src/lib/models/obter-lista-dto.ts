/* tslint:disable */
/* eslint-disable */
export interface ObterListaDto {
  descricao?: string | null;
  limit?: number | null;
  offset?: number | null;
  tipo: Array<'pessoas' | 'clientes' | 'pecas' | 'servicos' | 'tipoServicos' | 'veiculos' | 'itemServico'>;
}
