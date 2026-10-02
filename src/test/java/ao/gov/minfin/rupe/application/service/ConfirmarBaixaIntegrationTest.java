package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.ConfirmarBaixaCommand;
import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.port.in.ConfirmarBaixaUseCase;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataRupeRepository;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataServicoRepository;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.EmolumentoJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.ServicoJpaEntity;
import ao.gov.minfin.rupe.domain.enums.EstadoPagamento;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class ConfirmarBaixaIntegrationTest {

    @Autowired
    private GerarRupeUseCase gerarRupeUseCase;

    @Autowired
    private ConfirmarBaixaUseCase confirmarBaixaUseCase;

    @Autowired
    private SpringDataRupeRepository rupeRepository;

    @Autowired
    private SpringDataServicoRepository servicoRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeEach
    void prepararDados() {

        rupeRepository.deleteAll();
        jdbcTemplate.execute(
                "ALTER SEQUENCE rupe_sequencial_seq RESTART WITH 1"
        );

        servicoRepository.deleteAll();

        ServicoJpaEntity servico =
                new ServicoJpaEntity(
                        "SERV-001",
                        "Emissão de Certidão",
                        "0001",
                        "01",
                        true,
                        java.util.List.of(
                                new EmolumentoJpaEntity(
                                        "Taxa de emissão",
                                        new BigDecimal("5000.00")
                                )
                        )
                );

        servicoRepository.save(servico);
    }

    @Test
    void deveConfirmarPagamentoEResolverRUPE() {

        LocalDateTime expiracao =
                LocalDateTime.now().plusDays(7);

        var rupe = gerarRupeUseCase.executar(
                new GerarRupeCommand(
                        new Nif("123456789"),
                        "Alfredo Baptista",
                        "SERV-001",
                        expiracao
                )
        );

        assertEquals(
                EstadoPagamento.PENDENTE,
                rupe.getEstado()
        );

        String numeroRecibo = "REC-2026-000001";

        LocalDateTime dataPagamento =
                LocalDateTime.now();

        var rupePago =
                confirmarBaixaUseCase.executar(
                        new ConfirmarBaixaCommand(
                                rupe.getReferencia(),
                                numeroRecibo,
                                dataPagamento
                        )
                );

        assertEquals(
                EstadoPagamento.PAGO,
                rupePago.getEstado()
        );

        assertEquals(
                numeroRecibo,
                rupePago.getNumeroRecibo()
        );

        assertNotNull(
                rupePago.getDataPagamento()
        );

        assertEquals(
                1,
                rupeRepository.count()
        );

        var persistido =
                rupeRepository
                        .findByReferencia(
                                rupe.getReferencia()
                        )
                        .orElseThrow();

        assertEquals(
                EstadoPagamento.PAGO,
                persistido.getEstado()
        );

        assertEquals(
                numeroRecibo,
                persistido.getNumeroRecibo()
        );
    }
}