
package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.ServicoRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.entity.Servico;
import ao.gov.minfin.rupe.domain.valueobject.Emolumento;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class GerarRupeServiceTest {

@Test
void deveGerarRupeComReferenciaDe20Digitos() {

    RupeRepositoryPort rupeRepository =
            new RupeRepositoryPort() {

                @Override
                public Rupe guardar(Rupe rupe) {
                    return rupe;
                }

                @Override
                public Optional<Rupe> buscarPorReferencia(
                        String referencia
                ) {
                    return Optional.empty();
                }

                @Override
                public Optional<Rupe>
                buscarPorReferenciaComBloqueio(
                        String referencia
                ) {
                    return Optional.empty();
                }

                @Override
                public long obterProximoSequencial() {
                    return 1L;
                }
            };

    Servico servico = new Servico(
            "SERV-001",
            "Emissão de Certidão",
            "0001",
            "01",
            true,
            List.of(
                    new Emolumento(
                            "Taxa de emissão",
                            new BigDecimal("5000.00")
                    )
            )
    );

    ServicoRepositoryPort servicoRepository =
            codigo -> Optional.of(servico);

    GerarRupeService service =
            new GerarRupeService(
                    rupeRepository,
                    servicoRepository
            );

    GerarRupeCommand command =
            new GerarRupeCommand(
                    new Nif("123456789"),
                    "Alfredo Baptista",
                    "SERV-001",
                    LocalDateTime.now().plusDays(30)
            );

    Rupe rupe = service.executar(command);

    assertNotNull(rupe);

    assertEquals(
            20,
            rupe.getReferencia().length()
    );

    assertTrue(
            rupe.getReferencia().matches("\\d{20}")
    );

    assertEquals(
            "Alfredo Baptista",
            rupe.getContribuinte().getNome()
    );

    assertEquals(
            "SERV-001",
            rupe.getCodigoServico()
    );

    assertEquals(
            new BigDecimal("5000.00"),
            rupe.getValor()
    );

    assertEquals(
            "PENDENTE",
            rupe.getEstado().name()
    );
}

}
