package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.PaymentTransactionJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataPaymentTransactionRepository
        extends JpaRepository<PaymentTransactionJpaEntity, Long> {

    boolean existsByIdempotencyKey(String idempotencyKey);

    long countByReferencia(String referencia);
}