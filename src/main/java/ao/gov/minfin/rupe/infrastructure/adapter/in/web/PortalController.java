package ao.gov.minfin.rupe.infrastructure.adapter.in.web;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.port.in.ConsultarRupeUseCase;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.request.GerarRupeRequest;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.ConsultaRupeResponse;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.RupeResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/rupes")
public class PortalController {

    private final GerarRupeUseCase gerarRupeUseCase;
    private final ConsultarRupeUseCase consultarRupeUseCase;

    public PortalController(
            GerarRupeUseCase gerarRupeUseCase,
            ConsultarRupeUseCase consultarRupeUseCase
    ) {
        this.gerarRupeUseCase = gerarRupeUseCase;
        this.consultarRupeUseCase = consultarRupeUseCase;
    }

    @PostMapping
    public ResponseEntity<RupeResponse> gerar(
            @Valid @RequestBody GerarRupeRequest request
    ) {

        GerarRupeCommand command =
                new GerarRupeCommand(
                        new Nif(request.nif()),
                        request.nomeContribuinte(),
                        request.codigoServico(),
                        request.dataExpiracao()
                );

        Rupe rupe =
                gerarRupeUseCase.executar(command);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(RupeResponse.from(rupe));
    }

    @GetMapping("/{referencia}")
    public ResponseEntity<ConsultaRupeResponse> consultar(
            @PathVariable String referencia
    ) {

        Rupe rupe =
                consultarRupeUseCase.executar(referencia);

        return ResponseEntity.ok(
                ConsultaRupeResponse.from(rupe)
        );
    }
}