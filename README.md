# 🔧 Gestão de Oficina Mecânica

Sistema completo para gestão de serviços de uma oficina mecânica. Permite o registro e acompanhamento de atendimentos, controle de peças e serviços, e geração de relatórios financeiros.

**Stack:** Java 11 · PostgreSQL · Tomcat 9 · Angular

---

## 📋 Sobre o Projeto

O sistema cobre o ciclo completo de atendimento de uma oficina — do cadastro do cliente e veículo à abertura, execução e finalização de ordens de serviço — com relatórios de custo e lucro do estabelecimento.

### Funcionalidades

- Cadastro de pessoas, clientes e veículos
- Abertura e finalização de ordens de serviço
- Controle de peças e tipos de serviço
- Cálculo automático do valor total da ordem
- Relatórios sumarizados por veículo
- API REST para integração com o frontend
- Dashboard com resumo financeiro

---

## 🏗️ Arquitetura

O projeto é dividido em módulos Maven independentes:

```
gestao-oficina-mecanica/
├── conexao/       — gerenciamento de conexão com o banco
├── modulo/        — camada de modelo (entidades)
├── persiste/      — camada de persistência (DAOs)
├── negocio/       — camada de negócio (regras e validações)
└── oficina/       — API REST (controllers JAX-RS)
```

### Modelos principais

| Entidade       | Descrição                                      |
| -------------- | ---------------------------------------------- |
| `Pessoa`       | Base para clientes e proprietários             |
| `Cliente`      | Extends Pessoa, vinculado a serviços           |
| `Veiculo`      | Identificado pela placa, pertence a uma Pessoa |
| `Servico`      | Ordem de serviço vinculada a um Veículo        |
| `ServicoItem`  | Item da ordem — Peça ou TipoServico            |
| `ItemCatalogo` | Classe abstrata base para Peça e TipoServico   |

---

## 🚀 Como Rodar

### Pré-requisitos

- Java 11+
- Maven
- PostgreSQL
- Tomcat 9
- Node.js + Angular CLI

### Banco de Dados

1. Crie um banco chamado `oficina` no PostgreSQL
2. Execute o script SQL de criação das tabelas disponível em `docs/schema.sql`

### Backend (Java)

1. Na raiz do projeto, compile todos os módulos:

```bash
mvn clean install
```

2. Em cada módulo, execute **Run with Dependencies** pelo NetBeans, ou via Maven:

```bash
cd negocio
mvn exec:java
```

3. Copie o `.war` gerado em `oficina/target/` para a pasta `webapps` do Tomcat:

```bash
cp oficina/target/oficina.war /caminho/tomcat/webapps/
```

4. Inicie o Tomcat e acesse:

```
http://localhost:8080/oficina/pessoas
```

### Frontend (Angular)

```bash
cd frontend
npm install
ng serve
```

Acesse em `http://localhost:4200`

---

## 🔌 Endpoints da API

| Método | Endpoint                       | Descrição                |
| ------ | ------------------------------ | ------------------------ |
| GET    | `/pessoas`                     | Lista todas as pessoas   |
| GET    | `/pessoas?nome=x`              | Filtra por nome          |
| POST   | `/pessoas`                     | Cadastra pessoa          |
| GET    | `/veiculos`                    | Lista veículos           |
| GET    | `/veiculos/{placa}`            | Busca por placa          |
| POST   | `/veiculos`                    | Cadastra veículo         |
| GET    | `/servicos`                    | Lista serviços           |
| GET    | `/servicos?placa=x`            | Lista por veículo        |
| POST   | `/servicos`                    | Abre ordem de serviço    |
| PUT    | `/servicos/{numero}/finalizar` | Finaliza a ordem         |
| POST   | `/servicos/{numero}/item`      | Adiciona item à ordem    |
| GET    | `/pecas`                       | Lista catálogo de peças  |
| POST   | `/pecas`                       | Cadastra peça            |
| GET    | `/tiposervicos`                | Lista tipos de serviço   |
| POST   | `/tiposervicos`                | Cadastra tipo de serviço |

---

## 🛠️ Tecnologias

- **Java 11** — backend e regras de negócio
- **PostgreSQL** — banco de dados relacional
- **JDBC** — acesso ao banco sem ORM
- **JAX-RS / Jersey** — API REST
- **Tomcat 9** — servidor de aplicação
- **Angular** — frontend web
- **Maven** — gerenciamento de dependências e build

---

## 👨‍💻 Autor

Desenvolvido por **Gabriel** como projeto de conclusão de treinamento na **NSInova**.
