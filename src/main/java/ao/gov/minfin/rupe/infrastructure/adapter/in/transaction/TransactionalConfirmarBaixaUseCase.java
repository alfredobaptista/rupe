package ao.gov.minfin.rupe.infrastructure.adapter.in.transaction;

import ao.gov.minfin.rupe.application.command.ConfirmarBaixaCommand;
import ao.gov.minfin.rupe.application.port.in.ConfirmarBaixaUseCase;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import org.springframework.transaction.annotation.Transactional;

public class TransactionalConfirmarBaixaUseCase
        implements ConfirmarBaixaUseCase {

    private final ConfirmarBaixaUseCase delegate;

    public TransactionalConfirmarBaixaUseCase(
            ConfirmarBaixaUseCase delegate
    ) {
        this.delegate = delegate;
    }

    @Override
    @Transactional
    public Rupe executar(
            ConfirmarBaixaCommand command
    ) {
        return delegate.executar(command);
    }
}