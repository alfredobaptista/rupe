CREATE TABLE webhook_subscriptions (
    id BIGSERIAL PRIMARY KEY,

    nif VARCHAR(50) NOT NULL,
    url VARCHAR(500) NOT NULL,
    secret VARCHAR(255) NOT NULL,
    ativo BOOLEAN NOT NULL DEFAULT TRUE,

    criado_em TIMESTAMP NOT NULL DEFAULT NOW(),

    CONSTRAINT uk_webhook_subscription_nif
        UNIQUE (nif)
);

CREATE INDEX idx_webhook_subscription_ativo
    ON webhook_subscriptions (ativo);


CREATE TABLE webhook_events (
    id BIGSERIAL PRIMARY KEY,

    event_id UUID NOT NULL,
    referencia VARCHAR(20) NOT NULL,
    url_destino VARCHAR(500) NOT NULL,

    payload TEXT NOT NULL,

    estado VARCHAR(20) NOT NULL DEFAULT 'PENDENTE',
    tentativas INT NOT NULL DEFAULT 0,
    ultimo_erro VARCHAR(500),

    criado_em TIMESTAMP NOT NULL,
    proxima_tentativa_em TIMESTAMP NOT NULL,
    processado_em TIMESTAMP,

    CONSTRAINT uk_webhook_event_event_id
        UNIQUE (event_id),

    CONSTRAINT ck_webhook_event_estado
        CHECK (estado IN ('PENDENTE', 'ENVIADO', 'FALHADO'))
);

CREATE INDEX idx_webhook_event_pendentes
    ON webhook_events (estado, proxima_tentativa_em);

CREATE INDEX idx_webhook_event_referencia
    ON webhook_events (referencia);