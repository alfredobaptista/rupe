package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.port.in.ConsultarRupeUseCase;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.exception.RupeNaoEncontradoException;

public class ConsultarRupeService implements ConsultarRupeUseCase {

    private final RupeRepositoryPort rupeRepository;

    public ConsultarRupeService(
            RupeRepositoryPort rupeRepository
    ) {
        this.rupeRepository = rupeRepository;
    }

    @Override
    public Rupe executar(String referencia) {

        if (referencia == null || referencia.isBlank()) {
            throw new IllegalArgumentException(
                    "A referência RUPE é obrigatória."
            );
        }

        String referenciaNormalizada = referencia.trim();

        return rupeRepository
                .buscarPorReferencia(referenciaNormalizada)
                .orElseThrow(() ->
                        new RupeNaoEncontradoException(
                                referenciaNormalizada
                        )
                );
    }
}