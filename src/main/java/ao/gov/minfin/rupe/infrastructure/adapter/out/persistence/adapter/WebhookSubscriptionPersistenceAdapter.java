package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.adapter;

import ao.gov.minfin.rupe.application.port.out.WebhookSubscriptionPort;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataWebhookSubscriptionRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class WebhookSubscriptionPersistenceAdapter
        implements WebhookSubscriptionPort {

    private final SpringDataWebhookSubscriptionRepository repository;

    public WebhookSubscriptionPersistenceAdapter(
            SpringDataWebhookSubscriptionRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public Optional<SubscricaoWebhook> buscarPorNif(String nif) {

        return repository
                .findByNifAndAtivoTrue(nif)
                .map(entity -> new SubscricaoWebhook(
                        entity.getNif(),
                        entity.getUrl(),
                        entity.getSecret()
                ));
    }
}