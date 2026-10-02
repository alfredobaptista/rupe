CREATE TABLE payment_transactions (
    id BIGSERIAL PRIMARY KEY,

    idempotency_key VARCHAR(100) NOT NULL,

    referencia VARCHAR(20) NOT NULL,

    numero_recibo VARCHAR(100) NOT NULL,

    data_pagamento TIMESTAMP NOT NULL,

    processado_em TIMESTAMP NOT NULL,

    CONSTRAINT uk_payment_transaction_idempotency_key
        UNIQUE (idempotency_key)
);

CREATE INDEX idx_payment_transaction_referencia
    ON payment_transactions (referencia);