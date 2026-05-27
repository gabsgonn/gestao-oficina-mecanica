# PLANEJAMENTO END-POINTS

**Pessoa** `/pessoa`

- `GET /pessoa` — lista todas OK
- `GET /pessoa?nome=x` — filtra por nome OK
- `POST /pessoa` — cadastra OK

**Veículo** `/veiculo`

- `GET /veiculo` — lista todos
- `GET /veiculo/{placa}` — busca por placa
- `POST /veiculo` — cadastra

**Serviço** `/servico`

- `GET /servico` — lista todos
- `GET /servico?placa=x` — lista por veículo
- `POST /servico` — abre ordem
- `PUT /servico/{numero}/finalizar` — finaliza com cálculo do total

**ServicoItem** `/servico/{numero}/item`

- `POST /servico/{numero}/item` — adiciona item à ordem

**TipoServico** `/tiposervico`

- `GET /tiposervico` — lista catálogo
- `POST /tiposervico` — cadastra

**Peça** `/peca`

- `GET /peca` — lista catálogo
- `POST /peca` — cadastra

Esse conjunto cobre o ciclo completo — cadastro, atendimento e finalização. Para apresentação é mais do que suficiente.
