package ao.gov.minfin.rupe.application.webhook;

import ao.gov.minfin.rupe.domain.entity.Rupe;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WebhookPayload(
        String evento,
        UUID eventId,
        String referencia,
        String nif,
        String nomeContribuinte,
        BigDecimal valor,
        String numeroRecibo,
        LocalDateTime dataPagamento,
        LocalDateTime timestamp
) {

    public static WebhookPayload from(Rupe rupe, UUID eventId) {
        return new WebhookPayload(
                "RUPE_PAGO",
                eventId,
                rupe.getReferencia(),
                rupe.getContribuinte().getNif().getValor(),
                rupe.getContribuinte().getNome(),
                rupe.getValor(),
                rupe.getNumeroRecibo(),
                rupe.getDataPagamento(),
                LocalDateTime.now()
        );
    }
}
