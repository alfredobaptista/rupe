package ao.gov.minfin.rupe.infrastructure.adapter.in.web;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.port.in.ConsultarRupeUseCase;
import ao.gov.minfin.rupe.application.port.in.ConsultarServicosUseCase;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.request.GerarRupeRequest;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.ConsultaRupeResponse;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.RupeResponse;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.ServicoResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rupes")
public class PortalController {

    private final GerarRupeUseCase gerarRupeUseCase;
    private final ConsultarRupeUseCase consultarRupeUseCase;
    private final ConsultarServicosUseCase consultarServicosUseCase;

    public PortalController(
            GerarRupeUseCase gerarRupeUseCase,
            ConsultarRupeUseCase consultarRupeUseCase,
            ConsultarServicosUseCase consultarServicosUseCase
    ) {
        this.gerarRupeUseCase = gerarRupeUseCase;
        this.consultarRupeUseCase = consultarRupeUseCase;
        this.consultarServicosUseCase = consultarServicosUseCase;
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

    @GetMapping("/servicos")
    public ResponseEntity<List<ServicoResponse>> listarServicos() {

        List<ServicoResponse> response =
                consultarServicosUseCase.executar()
                        .stream()
                        .map(ServicoResponse::from)
                        .toList();

        return ResponseEntity.ok(response);
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