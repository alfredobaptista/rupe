package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.RupeJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataRupeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.time.LocalDateTime;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ActiveProfiles("test")
class RupeOptimisticLockingIntegrationTest {

    @Autowired
    private SpringDataRupeRepository repository;

    @Autowired
    private PlatformTransactionManager transactionManager;

    @Test
    void deveDetectarConflitoDeOptimisticLocking() {

        String referencia = "00010100000000000001";

        criarRupe(referencia);

        RupeJpaEntity rupeA = carregarEmTransacao(referencia);
        RupeJpaEntity rupeB = carregarEmTransacao(referencia);

        assert rupeA.getVersion() == 0;
        assert rupeB.getVersion() == 0;

        TransactionTemplate transactionTemplate =
                new TransactionTemplate(transactionManager);

        transactionTemplate.executeWithoutResult(status -> {

            rupeA.actualizarPagamento(
                    ao.gov.minfin.rupe.domain.enums.EstadoPagamento.PAGO,
                    "REC-A",
                    LocalDateTime.now()
            );

            repository.saveAndFlush(rupeA);
        });

        assertThrows(
                ObjectOptimisticLockingFailureException.class,
                () ->
                        transactionTemplate.executeWithoutResult(status -> {

                            rupeB.actualizarPagamento(
                                    ao.gov.minfin.rupe.domain.enums.EstadoPagamento.PAGO,
                                    "REC-B",
                                    LocalDateTime.now()
                            );

                            repository.saveAndFlush(rupeB);
                        })
        );
    }

    private void criarRupe(String referencia) {

        transactionTemplate().executeWithoutResult(status -> {

            RupeJpaEntity rupe = new RupeJpaEntity(
                    referencia,
                    "123456789",
                    "Contribuinte Teste",
                    "SERV-001",
                    "Emissão de Certidão",
                    new java.math.BigDecimal("5000.00"),
                    LocalDateTime.now(),
                    LocalDateTime.now().plusDays(30),
                    ao.gov.minfin.rupe.domain.enums.EstadoPagamento.PENDENTE,
                    null,
                    null
            );

            repository.saveAndFlush(rupe);
        });
    }

    private RupeJpaEntity carregarEmTransacao(
            String referencia
    ) {

        return transactionTemplate().execute(status ->
                repository.findByReferencia(referencia)
                        .orElseThrow()
        );
    }

    private TransactionTemplate transactionTemplate() {
        return new TransactionTemplate(transactionManager);
    }

    @AfterEach
void limpar() {

    transactionTemplate().executeWithoutResult(status ->
            repository.findByReferencia(
                    "00010100000000000001"
            ).ifPresent(repository::delete)
    );
}
}