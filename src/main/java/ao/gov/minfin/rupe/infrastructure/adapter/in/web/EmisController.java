package ao.gov.minfin.rupe.infrastructure.adapter.in.web;

import ao.gov.minfin.rupe.application.command.ProcessarPagamentoCommand;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.request.ConfirmarPagamentoRequest;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.RupeResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class EmisController {

    private final ProcessarPagamentoUseCase processarPagamentoUseCase;

    public EmisController(
            ProcessarPagamentoUseCase processarPagamentoUseCase
    ) {
        this.processarPagamentoUseCase = processarPagamentoUseCase;
    }

    @PostMapping
    public ResponseEntity<RupeResponse> processarPagamento(
            @RequestHeader("Idempotency-Key") String idempotencyKey,
            @Valid @RequestBody ConfirmarPagamentoRequest request
    ) {

        ProcessarPagamentoCommand command = new ProcessarPagamentoCommand(
                idempotencyKey,
                request.referencia(),
                request.numeroRecibo(),
                request.dataPagamento()
        );

        Rupe rupe = processarPagamentoUseCase.executar(command);

        return ResponseEntity.ok(
                RupeResponse.from(rupe)
        );
    }
}