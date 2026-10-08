package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.WebhookSubscriptionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataWebhookSubscriptionRepository
        extends JpaRepository<WebhookSubscriptionJpaEntity, Long> {

    Optional<WebhookSubscriptionJpaEntity> findByNifAndAtivoTrue(String nif);
}