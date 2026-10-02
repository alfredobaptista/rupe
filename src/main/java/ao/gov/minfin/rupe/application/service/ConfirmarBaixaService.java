package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.ConfirmarBaixaCommand;
import ao.gov.minfin.rupe.application.port.in.ConfirmarBaixaUseCase;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.exception.RupeNaoEncontradoException;

public class ConfirmarBaixaService implements ConfirmarBaixaUseCase {

    private final RupeRepositoryPort rupeRepository;

    public ConfirmarBaixaService(
            RupeRepositoryPort rupeRepository
    ) {
        this.rupeRepository = rupeRepository;
    }

    @Override
    public Rupe executar(ConfirmarBaixaCommand command) {

        Rupe rupe = rupeRepository
                .buscarPorReferencia(command.referencia().trim())
                .orElseThrow(() ->
                        new RupeNaoEncontradoException(
                                command.referencia()
                        )
                );

        rupe.confirmarPagamento(
                command.numeroRecibo(),
                command.dataPagamento()
        );

        return rupeRepository.guardar(rupe);
    }
}