package ao.gov.minfin.rupe.application.port.out;

import java.util.Optional;

public interface WebhookSubscriptionPort {

    Optional<SubscricaoWebhook> buscarPorNif(String nif);

    record SubscricaoWebhook(
            String nif,
            String url,
            String secret
    ) {
    }
}