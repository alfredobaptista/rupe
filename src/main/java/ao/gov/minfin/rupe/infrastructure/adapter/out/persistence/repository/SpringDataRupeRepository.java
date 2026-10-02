package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.RupeJpaEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface SpringDataRupeRepository
        extends JpaRepository<RupeJpaEntity, Long> {

    Optional<RupeJpaEntity> findByReferencia(String referencia);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<RupeJpaEntity> findWithLockByReferencia(
            String referencia
    );
}