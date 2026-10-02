-- Actualiza a constraint do estado do RUPE.
-- ABERTO substitui PENDENTE como estado inicial.

ALTER TABLE rupes
    DROP CONSTRAINT ck_rupe_estado;

UPDATE rupes
SET estado = 'ABERTO'
WHERE estado = 'PENDENTE';

ALTER TABLE rupes
    ADD CONSTRAINT ck_rupe_estado
    CHECK (
        estado IN (
            'ABERTO',
            'PAGO',
            'EXPIRADO',
            'CANCELADO'
        )
    );