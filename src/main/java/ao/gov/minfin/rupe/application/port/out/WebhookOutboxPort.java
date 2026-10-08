package ao.gov.minfin.rupe.application.port.out;

import java.util.UUID;

public interface WebhookOutboxPort {

    void enfileirar(
            UUID eventId,
            String referencia,
            String urlDestino,
            String secret,
            String payload
    );
}