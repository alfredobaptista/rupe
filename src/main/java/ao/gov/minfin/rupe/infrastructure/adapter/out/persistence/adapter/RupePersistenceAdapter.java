package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.adapter;

import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Contribuinte;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.RupeJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.RupeSequenceRepository;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataRupeRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RupePersistenceAdapter implements RupeRepositoryPort {

    private final SpringDataRupeRepository repository;
    private final RupeSequenceRepository sequenceRepository;

    public RupePersistenceAdapter(
            SpringDataRupeRepository repository,
            RupeSequenceRepository sequenceRepository
    ) {
        this.repository = repository;
        this.sequenceRepository = sequenceRepository;
    }

    @Override
    public Rupe guardar(Rupe rupe) {

        RupeJpaEntity entity = repository
                .findByReferencia(rupe.getReferencia())
                .map(existing -> actualizarExistente(existing, rupe))
                .orElseGet(() -> toEntity(rupe));

        RupeJpaEntity saved = repository.save(entity);

        return toDomain(saved);
    }

    private RupeJpaEntity actualizarExistente(
            RupeJpaEntity entity,
            Rupe rupe
    ) {
        entity.actualizarPagamento(
                rupe.getEstado(),
                rupe.getNumeroRecibo(),
                rupe.getDataPagamento()
        );

        return entity;
    }

    @Override
    public Optional<Rupe> buscarPorReferencia(
            String referencia
    ) {
        return repository
                .findByReferencia(referencia)
                .map(this::toDomain);
    }

    @Override
    public Optional<Rupe> buscarPorReferenciaComBloqueio(
            String referencia
    ) {
        return repository
                .findWithLockByReferencia(referencia)
                .map(this::toDomain);
    }

    @Override
    public long obterProximoSequencial() {
        return sequenceRepository.obterProximoSequencial();
    }

    private Rupe toDomain(RupeJpaEntity entity) {

        Contribuinte contribuinte = new Contribuinte(
                new Nif(entity.getNifContribuinte()),
                entity.getNomeContribuinte()
        );

        Rupe rupe = new Rupe(
                entity.getReferencia(),
                contribuinte,
                entity.getCodigoServico(),
                entity.getDescricaoServico(),
                entity.getValor(),
                entity.getDataEmissao(),
                entity.getDataExpiracao()
        );

        restaurarEstado(rupe, entity);

        return rupe;
    }

    private void restaurarEstado(
            Rupe rupe,
            RupeJpaEntity entity
    ) {

        switch (entity.getEstado()) {

            case ABERTO -> {
                // Estado inicial do RUPE.
            }

            case PAGO -> rupe.confirmarPagamento(
                    entity.getNumeroRecibo(),
                    entity.getDataPagamento()
            );

            case EXPIRADO -> rupe.expirar();

            case CANCELADO -> rupe.cancelar();
        }
    }

    private RupeJpaEntity toEntity(Rupe rupe) {

        return new RupeJpaEntity(
                rupe.getReferencia(),
                rupe.getContribuinte()
                        .getNif()
                        .getValor(),
                rupe.getContribuinte()
                        .getNome(),
                rupe.getCodigoServico(),
                rupe.getDescricaoServico(),
                rupe.getValor(),
                rupe.getDataEmissao(),
                rupe.getDataExpiracao(),
                rupe.getEstado(),
                rupe.getNumeroRecibo(),
                rupe.getDataPagamento()
        );
    }
}