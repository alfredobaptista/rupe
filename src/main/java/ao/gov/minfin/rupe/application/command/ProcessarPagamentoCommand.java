package ao.gov.minfin.rupe.application.command;

import java.time.LocalDateTime;
import java.util.Objects;

public record ProcessarPagamentoCommand(
        String idempotencyKey,
        String referencia,
        String numeroRecibo,
        LocalDateTime dataPagamento
) {

    public ProcessarPagamentoCommand {

        if (idempotencyKey == null || idempotencyKey.isBlank()) {
            throw new IllegalArgumentException(
                    "A chave de idempotência é obrigatória."
            );
        }

        if (referencia == null || referencia.isBlank()) {
            throw new IllegalArgumentException(
                    "A referência RUPE é obrigatória."
            );
        }

        if (numeroRecibo == null || numeroRecibo.isBlank()) {
            throw new IllegalArgumentException(
                    "O número do recibo é obrigatório."
            );
        }

        Objects.requireNonNull(
                dataPagamento,
                "A data do pagamento é obrigatória."
        );
    }
}