package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.WebhookEventJpaEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface SpringDataWebhookEventRepository
        extends JpaRepository<WebhookEventJpaEntity, Long> {

    @Query("""
            SELECT w FROM WebhookEventJpaEntity w
            WHERE w.estado = 'PENDENTE'
              AND w.proximaTentativaEm <= :agora
            ORDER BY w.criadoEm
            """)
    List<WebhookEventJpaEntity> buscarPendentes(
            @Param("agora") LocalDateTime agora,
            Pageable pageable
    );
}