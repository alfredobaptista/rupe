package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.ServicoJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataServicoRepository
        extends JpaRepository<ServicoJpaEntity, Long> {

    @Query("""
            SELECT DISTINCT s
            FROM ServicoJpaEntity s
            LEFT JOIN FETCH s.emolumentos
            WHERE s.codigo = :codigo
            """)
    Optional<ServicoJpaEntity> findByCodigoComEmolumentos(String codigo);

    @Query("""
            SELECT DISTINCT s
            FROM ServicoJpaEntity s
            LEFT JOIN FETCH s.emolumentos
            WHERE s.ativo = true
            ORDER BY s.codigo
            """)
    List<ServicoJpaEntity> findAllComEmolumentos();
}