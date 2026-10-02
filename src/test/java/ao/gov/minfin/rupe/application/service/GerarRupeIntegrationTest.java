package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataRupeRepository;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataServicoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class GerarRupeIntegrationTest {

    @Autowired
    private GerarRupeUseCase gerarRupeUseCase;

    @Autowired
    private SpringDataRupeRepository rupeRepository;

    @Autowired
    private SpringDataServicoRepository servicoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void limparDados() {
        jdbcTemplate.execute("DELETE FROM rupes");
        jdbcTemplate.execute("DELETE FROM emolumentos");
        jdbcTemplate.execute("DELETE FROM servicos");

        jdbcTemplate.execute(
                "ALTER SEQUENCE rupe_sequencial_seq RESTART WITH 1"
        );
    }

    @Test
    void deveGerarEResolverPersistirRupe() {

        jdbcTemplate.update("""
                INSERT INTO servicos (
                    codigo,
                    nome,
                    codigo_organismo,
                    codigo_modulo,
                    ativo
                )
                VALUES (?, ?, ?, ?, ?)
                """,
                "SERV-001",
                "Emissão de Certidão",
                "0001",
                "01",
                true
        );

        Long servicoId = jdbcTemplate.queryForObject(
                """
                SELECT id
                FROM servicos
                WHERE codigo = ?
                """,
                Long.class,
                "SERV-001"
        );

        jdbcTemplate.update("""
                INSERT INTO emolumentos (
                    servico_id,
                    nome,
                    valor
                )
                VALUES (?, ?, ?)
                """,
                servicoId,
                "Taxa de emissão",
                5000.00
        );

        GerarRupeCommand command =
                new GerarRupeCommand(
                        new Nif("123456789"),
                        "Alfredo Baptista",
                        "SERV-001",
                        LocalDateTime.now().plusDays(30)
                );

        Rupe rupe = gerarRupeUseCase.executar(command);

        assertNotNull(rupe);

        assertNotNull(rupe.getReferencia());

        assertEquals(
                20,
                rupe.getReferencia().length()
        );

        assertTrue(
                rupe.getReferencia().matches("\\d{20}")
        );

        assertEquals(
                "0001",
                rupe.getReferencia().substring(0, 4)
        );

        assertEquals(
                "01",
                rupe.getReferencia().substring(4, 6)
        );

        assertEquals(
                "ABERTO",
                rupe.getEstado().name()
        );

        assertEquals(
                "SERV-001",
                rupe.getCodigoServico()
        );

        assertEquals(
                "5000.00",
                rupe.getValor().toPlainString()
        );

        assertEquals(
                1,
                rupeRepository.count()
        );

        var persistido =
                rupeRepository.findByReferencia(
                        rupe.getReferencia()
                );

        assertTrue(persistido.isPresent());

        assertEquals(
                rupe.getReferencia(),
                persistido.get().getReferencia()
        );

        assertEquals(
                "5000.00",
                persistido.get().getValor().toPlainString()
        );
    }
}