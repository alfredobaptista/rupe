package ao.gov.minfin.rupe.application.port.out;

import ao.gov.minfin.rupe.domain.entity.Rupe;

import java.util.Optional;

public interface RupeRepositoryPort {

    Rupe guardar(Rupe rupe);

    Optional<Rupe> buscarPorReferencia(String referencia);

    Optional<Rupe> buscarPorReferenciaComBloqueio(
            String referencia
    );

    long obterProximoSequencial();
}