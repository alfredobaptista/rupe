package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.ProcessarPagamentoCommand;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.application.port.out.PaymentTransactionRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.exception.RupeNaoEncontradoException;

import java.time.LocalDateTime;

public class ProcessarPagamentoService
        implements ProcessarPagamentoUseCase {

    private final RupeRepositoryPort rupeRepository;
    private final PaymentTransactionRepositoryPort
            paymentTransactionRepository;

    public ProcessarPagamentoService(
            RupeRepositoryPort rupeRepository,
            PaymentTransactionRepositoryPort paymentTransactionRepository
    ) {
        this.rupeRepository = rupeRepository;
        this.paymentTransactionRepository =
                paymentTransactionRepository;
    }

@Override
public Rupe executar(
        ProcessarPagamentoCommand command
) {

    String idempotencyKey =
            command.idempotencyKey().trim();

    String referencia =
            command.referencia().trim();

    /*
     * Fast path:
     *
     * Se o evento já foi processado, não precisamos
     * adquirir o lock do RUPE.
     */
    if (paymentTransactionRepository
            .existePorIdempotencyKey(idempotencyKey)) {

        return rupeRepository
                .buscarPorReferencia(referencia)
                .orElseThrow(() ->
                        new RupeNaoEncontradoException(
                                referencia
                        )
                );
    }

    /*
     * Lock pessimista sobre o RUPE.
     */
    Rupe rupe = rupeRepository
            .buscarPorReferenciaComBloqueio(referencia)
            .orElseThrow(() ->
                    new RupeNaoEncontradoException(
                            referencia
                    )
            );

    /*
     * Segunda verificação de idempotência.
     *
     * Necessária para concorrência.
     */
    if (paymentTransactionRepository
            .existePorIdempotencyKey(idempotencyKey)) {

        return rupeRepository
                .buscarPorReferencia(referencia)
                .orElseThrow(() ->
                        new RupeNaoEncontradoException(
                                referencia
                        )
                );
    }

    rupe.confirmarPagamento(
            command.numeroRecibo(),
            command.dataPagamento()
    );

    Rupe rupeActualizado =
            rupeRepository.guardar(rupe);

    paymentTransactionRepository.registar(
            idempotencyKey,
            referencia,
            command.numeroRecibo(),
            command.dataPagamento(),
            LocalDateTime.now()
    );

    return rupeActualizado;
}

}