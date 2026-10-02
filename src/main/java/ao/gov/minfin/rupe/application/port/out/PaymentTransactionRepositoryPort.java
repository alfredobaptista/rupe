package ao.gov.minfin.rupe.application.port.out;

import java.time.LocalDateTime;

public interface PaymentTransactionRepositoryPort {

    boolean existePorIdempotencyKey(String idempotencyKey);

    ResultadoRegistoPagamento registar(
            String idempotencyKey,
            String referencia,
            String numeroRecibo,
            LocalDateTime dataPagamento,
            LocalDateTime processadoEm
    );

    long contarPorReferencia(String referencia);
}