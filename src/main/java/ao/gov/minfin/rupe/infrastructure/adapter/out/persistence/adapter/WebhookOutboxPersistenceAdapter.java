package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.adapter;

import ao.gov.minfin.rupe.application.port.out.WebhookOutboxPort;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.WebhookEventJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataWebhookEventRepository;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class WebhookOutboxPersistenceAdapter
        implements WebhookOutboxPort {

    private final SpringDataWebhookEventRepository repository;

    public WebhookOutboxPersistenceAdapter(
            SpringDataWebhookEventRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public void enfileirar(
            UUID eventId,
            String referencia,
            String urlDestino,
            String secret,
            String payload
    ) {
        LocalDateTime agora = LocalDateTime.now();

        repository.save(new WebhookEventJpaEntity(
                eventId,
                referencia,
                urlDestino,
                secret,
                payload,
                "PENDENTE",
                0,
                null,
                agora,
                agora,
                null
        ));
    }
}