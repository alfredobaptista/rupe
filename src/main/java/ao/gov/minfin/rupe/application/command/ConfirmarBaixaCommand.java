package ao.gov.minfin.rupe.application.command;

import java.time.LocalDateTime;

import java.util.Objects;

public record ConfirmarBaixaCommand(
        String referencia,
        String numeroRecibo,
        LocalDateTime dataPagamento
) {

    public ConfirmarBaixaCommand {
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