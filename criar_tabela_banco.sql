CREATE TABLE pessoa (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    nome        VARCHAR(255) NOT NULL,
    cpf         VARCHAR(14)  NOT NULL UNIQUE,
    email       VARCHAR(255),
    nascimento  DATE         NOT NULL
);

CREATE TABLE veiculo (
    placa          VARCHAR(8)   PRIMARY KEY,
    proprietario   UUID         NOT NULL,
    FOREIGN KEY (proprietario) REFERENCES pessoa(id)
);

CREATE TABLE servico (
    numero          SERIAL       PRIMARY KEY,
    descricao       VARCHAR(512) NOT NULL,
    data_inicio     TIMESTAMPTZ  NOT NULL,
    data_finalizacao TIMESTAMPTZ,
    valor           NUMERIC(17,2),
    placa_veiculo   VARCHAR(8)   NOT NULL,
    FOREIGN KEY (placa_veiculo) REFERENCES veiculo(placa)
);

CREATE TABLE tipo_servico (
    codigo          VARCHAR(12)  PRIMARY KEY,
    descricao       VARCHAR(512) NOT NULL,
    valor           NUMERIC(17,2) NOT NULL,
    tempo_estimado  INT          NOT NULL
);

CREATE TABLE peca (
    codigo    VARCHAR(12)   PRIMARY KEY,
    descricao VARCHAR(512)  NOT NULL,
    valor     NUMERIC(17,2) NOT NULL,
    estoque   INT           NOT NULL DEFAULT 0
);

CREATE TABLE servico_item (
    id          SERIAL        PRIMARY KEY,
    servico     INT           NOT NULL,
    tipo        VARCHAR(1)    NOT NULL CHECK (tipo IN ('P', 'S')),
    codigo      VARCHAR(12)   NOT NULL,
    quantidade  NUMERIC(17,5) NOT NULL,
    descricao   VARCHAR(512),
    FOREIGN KEY (servico) REFERENCES servico(numero)
);
