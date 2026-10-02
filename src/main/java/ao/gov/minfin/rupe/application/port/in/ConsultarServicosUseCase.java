package ao.gov.minfin.rupe.application.port.in;

import ao.gov.minfin.rupe.domain.entity.Servico;

import java.util.List;

public interface ConsultarServicosUseCase {

    List<Servico> executar();
}