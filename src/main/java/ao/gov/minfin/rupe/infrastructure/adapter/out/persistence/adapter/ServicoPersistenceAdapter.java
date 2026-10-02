package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.adapter;

import ao.gov.minfin.rupe.application.port.out.ServicoRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Servico;
import ao.gov.minfin.rupe.domain.valueobject.Emolumento;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.EmolumentoJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.ServicoJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataServicoRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ServicoPersistenceAdapter
        implements ServicoRepositoryPort {

    private final SpringDataServicoRepository repository;

    public ServicoPersistenceAdapter(
            SpringDataServicoRepository repository
    ) {
        this.repository = repository;
    }


    @Override
public Optional<Servico> buscarPorCodigo(String codigo) {

    return repository
            .findByCodigoComEmolumentos(codigo)
            .map(this::toDomain);
}

    private Servico toDomain(
            ServicoJpaEntity entity
    ) {

        List<Emolumento> emolumentos =
                entity.getEmolumentos()
                        .stream()
                        .map(this::toDomain)
                        .toList();

        return new Servico(
                entity.getCodigo(),
                entity.getNome(),
                entity.getCodigoOrganismo(),
                entity.getCodigoModulo(),
                entity.isAtivo(),
                emolumentos
        );
    }

    @Override
public List<Servico> listarTodos() {
    return repository.findAllComEmolumentos()
            .stream()
            .map(this::toDomain)
            .toList();
}

    private Emolumento toDomain(
            EmolumentoJpaEntity entity
    ) {
        return new Emolumento(
                entity.getNome(),
                entity.getValor()
        );
    }
}