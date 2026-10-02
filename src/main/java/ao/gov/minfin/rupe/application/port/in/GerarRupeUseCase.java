package ao.gov.minfin.rupe.application.port.in;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.domain.entity.Rupe;

public interface GerarRupeUseCase {

    Rupe executar(GerarRupeCommand command);
}