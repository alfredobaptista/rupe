CREATE SEQUENCE rupe_sequencial_seq
    START WITH 1
    INCREMENT BY 1
    MINVALUE 1
    NO MAXVALUE
    CACHE 50;


CREATE TABLE servicos (
    id BIGSERIAL PRIMARY KEY,

    codigo VARCHAR(50) NOT NULL,
    nome VARCHAR(200) NOT NULL,

    codigo_organismo VARCHAR(4) NOT NULL,
    codigo_modulo VARCHAR(2) NOT NULL,

    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    CONSTRAINT uk_servico_codigo
        UNIQUE (codigo),

    CONSTRAINT ck_servico_codigo_organismo
        CHECK (codigo_organismo ~ '^[0-9]{4}$'),

    CONSTRAINT ck_servico_codigo_modulo
        CHECK (codigo_modulo ~ '^[0-9]{2}$')
);


CREATE TABLE emolumentos (
    id BIGSERIAL PRIMARY KEY,

    servico_id BIGINT NOT NULL,

    nome VARCHAR(150) NOT NULL,

    valor NUMERIC(19, 2) NOT NULL,

    CONSTRAINT fk_emolumento_servico
        FOREIGN KEY (servico_id)
        REFERENCES servicos (id)
        ON DELETE CASCADE,

    CONSTRAINT ck_emolumento_valor
        CHECK (valor > 0)
);


CREATE INDEX idx_emolumento_servico
    ON emolumentos (servico_id);


CREATE TABLE rupes (
    id BIGSERIAL PRIMARY KEY,

    referencia VARCHAR(20) NOT NULL,

    nif_contribuinte VARCHAR(50) NOT NULL,
    nome_contribuinte VARCHAR(200) NOT NULL,

    codigo_servico VARCHAR(50) NOT NULL,
    descricao_servico VARCHAR(200) NOT NULL,

    valor NUMERIC(19, 2) NOT NULL,

    data_emissao TIMESTAMP NOT NULL,
    data_expiracao TIMESTAMP NOT NULL,

    estado VARCHAR(20) NOT NULL,

    numero_recibo VARCHAR(100),
    data_pagamento TIMESTAMP,

    CONSTRAINT uk_rupe_referencia
        UNIQUE (referencia),

    CONSTRAINT ck_rupe_valor
        CHECK (valor > 0),

    CONSTRAINT ck_rupe_datas
        CHECK (data_expiracao > data_emissao),

    CONSTRAINT ck_rupe_estado
        CHECK (
            estado IN (
                'PENDENTE',
                'PAGO',
                'EXPIRADO',
                'CANCELADO'
            )
        )
);


CREATE INDEX idx_rupe_estado
    ON rupes (estado);


CREATE INDEX idx_rupe_expiracao
    ON rupes (data_expiracao);


CREATE INDEX idx_rupe_nif
    ON rupes (nif_contribuinte);