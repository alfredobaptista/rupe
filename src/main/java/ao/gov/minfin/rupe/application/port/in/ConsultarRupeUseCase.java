package ao.gov.minfin.rupe.application.port.in;

import ao.gov.minfin.rupe.domain.entity.Rupe;

public interface ConsultarRupeUseCase {

    Rupe executar(String referencia);
}