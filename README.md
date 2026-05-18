# Sistema de Gestão de Oficina Mecânica

## Escopo Inicial

- **Pessoa / Cliente**
- **Serviço**
- **Veículo**
  - Carro / Moto / Caminhão / Caminhonete
  - Serviço: tipo · tempo · valor
- **Prestação de Serviços** — preço / orçamento
- Cadastro de Cliente
- Cadastro de Serviço — tipo, estado, orçamento, previsão de entrega
- Cadastro de Veículo
- **Dashboard** com resumo financeiro: quantidade de atendimentos · orçamentos por período

---

## Objetivo

O sistema desenvolvido na conclusão do treinamento registrará serviços de uma Oficina Mecânica, permitindo obter informações para a gestão do atendimento dos serviços prestados e relatórios de custo e lucros do estabelecimento.

---

## Modelos

Implementar as camadas de **Modelo**, **Persistência**, **Negócio** e **CLI ou API** dos seguintes modelos:

### Pessoa
| Campo | Tipo |
|---|---|
| id | uuid |
| nome completo | varchar(255) |
| data nascimento | timestamptz |
| cpf/cnpj | varchar(14) |

### Cadastro Cliente
| Campo | Tipo |
|---|---|
| pessoa | Pessoa |
| serviços | Serviço[] |

### Tipo Serviço
| Campo | Tipo |
|---|---|
| codigo | varchar(12) |
| descricao | varchar(512) |
| valor | numeric(17,2) |

### Peça
| Campo | Tipo |
|---|---|
| codigo | varchar(12) |
| valor | numeric(17,2) |
| descricao | varchar(512) |

### Serviço
| Campo | Tipo |
|---|---|
| descricao | varchar(12) |
| numero | sequência |
| data inicio | timestamptz |
| data finalizacao | timestamptz |
| valor | numeric(17,2) |
| veiculo | Veiculo |

### Serviço Item
| Campo | Tipo |
|---|---|
| servico | Servico |
| tipo | P / S |
| codigo | varchar(12) — código do tipo serviço ou peça |
| quantidade | numeric(17,5) |
| descricao | varchar(512) |

### Veículo
| Campo | Tipo |
|---|---|
| placa | varchar(8) |
| proprietario | Pessoa |

---

## Camada de Persistência

### Pessoa
- `manter` (CUD)
- `obterLista` (R)
- `obterPorId` (R)

### Serviço
- `manter` (CUD)
- `obterLista` (R)
- `obterPorId` (R)

### Veículo
- `manter` (CUD)
- `obterLista` (R)
- `obterPorPlaca` (R)

---

## Programa em Linha de Comando (Java)

- Cadastrar Pessoa
- Cadastrar Tipo de Serviço
- Cadastrar Peça
- Cadastro de Serviços
- Cadastrar Veículo
- Obter Serviço
- Listar Serviços
- **Listar Serviços Sumarizados** — para cada veículo, listar a quantidade de serviços e o valor somado

**Exemplo de saída:**

```
Placa    | Quantidade de Serviços | Valor Total
ABC1D23  | 5 serviços             | R$ 8.500,00
XYZ9K88  | 2 serviços             | R$ 1.200,00
```

---

## Camada da API (Controle)

- Usuário
- Pessoa
- Cliente
- Veículo
- Peça
- Tipo Serviço
- Serviço

---

## Frontend Web (Angular)

### Componentes
- Grid
- Visão Geral

### Funcionalidades
- Peças
- Tipos de Serviços
- Prestação de Serviço
- Relatórios