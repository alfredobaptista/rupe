package ao.gov.minfin.rupe.application.port.out;

import ao.gov.minfin.rupe.domain.entity.Servico;

import java.util.List;
import java.util.Optional;

public interface ServicoRepositoryPort {

    Optional<Servico> buscarPorCodigo(String codigo);

    List<Servico> listarTodos();
}