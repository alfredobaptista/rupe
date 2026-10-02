package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.adapter;

import ao.gov.minfin.rupe.application.port.out.PaymentTransactionRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.ResultadoRegistoPagamento;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.PaymentTransactionJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataPaymentTransactionRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Component
public class PaymentTransactionPersistenceAdapter
        implements PaymentTransactionRepositoryPort {

    private final SpringDataPaymentTransactionRepository repository;

    public PaymentTransactionPersistenceAdapter(
            SpringDataPaymentTransactionRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existePorIdempotencyKey(
            String idempotencyKey
    ) {
        return repository.existsByIdempotencyKey(
                idempotencyKey
        );
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public ResultadoRegistoPagamento registar(
            String idempotencyKey,
            String referencia,
            String numeroRecibo,
            LocalDateTime dataPagamento,
            LocalDateTime processadoEm
    ) {

        try {

            repository.saveAndFlush(
                    new PaymentTransactionJpaEntity(
                            idempotencyKey,
                            referencia,
                            numeroRecibo,
                            dataPagamento,
                            processadoEm
                    )
            );

            return ResultadoRegistoPagamento.REGISTADO;

        } catch (DataIntegrityViolationException exception) {

            return ResultadoRegistoPagamento.JA_EXISTENTE;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public long contarPorReferencia(String referencia) {
        return repository.countByReferencia(referencia);
    }
}