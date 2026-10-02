package ao.gov.minfin.rupe.application.port.in;

import ao.gov.minfin.rupe.application.command.ProcessarPagamentoCommand;
import ao.gov.minfin.rupe.domain.entity.Rupe;

public interface ProcessarPagamentoUseCase {

    Rupe executar(ProcessarPagamentoCommand command);
}