package ao.gov.minfin.rupe.infrastructure.adapter.in.transaction;

import ao.gov.minfin.rupe.application.command.ProcessarPagamentoCommand;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalProcessarPagamentoUseCase
        implements ProcessarPagamentoUseCase {

    private final ProcessarPagamentoUseCase delegate;

    public TransactionalProcessarPagamentoUseCase(
            ProcessarPagamentoUseCase delegate
    ) {
        this.delegate = delegate;
    }

    @Override
    @Transactional
    public Rupe executar(
            ProcessarPagamentoCommand command
    ) {
        return delegate.executar(command);
    }
}