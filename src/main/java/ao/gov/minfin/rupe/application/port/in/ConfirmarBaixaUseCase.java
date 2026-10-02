package ao.gov.minfin.rupe.application.port.in;

import ao.gov.minfin.rupe.application.command.ConfirmarBaixaCommand;
import ao.gov.minfin.rupe.domain.entity.Rupe;

public interface ConfirmarBaixaUseCase {

    Rupe executar(ConfirmarBaixaCommand command);
}