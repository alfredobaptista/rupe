package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.command.ProcessarPagamentoCommand;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.application.port.out.PaymentTransactionRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.enums.EstadoPagamento;
import ao.gov.minfin.rupe.domain.valueobject.Nif;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ActiveProfiles("test")
class ProcessarPagamentoServiceIntegrationTest {

    @Autowired
    private GerarRupeUseCase gerarRupeUseCase;

    @Autowired
    private ProcessarPagamentoUseCase processarPagamentoUseCase;

    @Autowired
    private PaymentTransactionRepositoryPort
            paymentTransactionRepository;

    @Test
    void deveProcessarMesmoEventoApenasUmaVez() {

        Rupe rupe = gerarRupeUseCase.executar(
                new GerarRupeCommand(
                        new Nif("123456789"),
                        "Contribuinte Idempotencia",
                        "SERV-001",
                        LocalDateTime.now().plusDays(30)
                )
        );

        String referencia = rupe.getReferencia();

        String idempotencyKey =
        "EMIS-IDEMPOTENCY-" + UUID.randomUUID();

        LocalDateTime dataPagamento =
                LocalDateTime.now();

        ProcessarPagamentoCommand command =
                new ProcessarPagamentoCommand(
                        idempotencyKey,
                        referencia,
                        "REC-IDEMP-001",
                        dataPagamento
                );

        Rupe primeiroResultado =
                processarPagamentoUseCase.executar(command);

        assertEquals(
                EstadoPagamento.PAGO,
                primeiroResultado.getEstado()
        );

        Rupe segundoResultado =
                processarPagamentoUseCase.executar(command);

        assertEquals(
                EstadoPagamento.PAGO,
                segundoResultado.getEstado()
        );

        assertEquals(
                "REC-IDEMP-001",
                segundoResultado.getNumeroRecibo()
        );

        assertEquals(
                1,
                paymentTransactionRepository
                        .contarPorReferencia(referencia)
        );
    }

    @Test
    void deveGarantirIdempotenciaQuandoMesmoEventoForProcessadoEmParalelo()
            throws Exception {

        Rupe rupe = gerarRupeUseCase.executar(
                new GerarRupeCommand(
                        new Nif("987654321"),
                        "Contribuinte Concorrencia",
                        "SERV-001",
                        LocalDateTime.now().plusDays(30)
                )
        );

        String referencia = rupe.getReferencia();

        String idempotencyKey =
        "EMIS-CONCURRENT-" + UUID.randomUUID();

        LocalDateTime dataPagamento =
                LocalDateTime.now();

        ProcessarPagamentoCommand command =
                new ProcessarPagamentoCommand(
                        idempotencyKey,
                        referencia,
                        "REC-CONCURRENT-001",
                        dataPagamento
                );

        int numeroThreads = 2;

        ExecutorService executor =
                Executors.newFixedThreadPool(numeroThreads);

        CountDownLatch inicio =
                new CountDownLatch(1);

        Future<Rupe> resultadoA =
                executor.submit(() -> {
                    inicio.await();
                    return processarPagamentoUseCase
                            .executar(command);
                });

        Future<Rupe> resultadoB =
                executor.submit(() -> {
                    inicio.await();
                    return processarPagamentoUseCase
                            .executar(command);
                });

        inicio.countDown();

        Rupe rupeA = resultadoA.get();
        Rupe rupeB = resultadoB.get();

        executor.shutdown();

        assertEquals(
                EstadoPagamento.PAGO,
                rupeA.getEstado()
        );

        assertEquals(
                EstadoPagamento.PAGO,
                rupeB.getEstado()
        );

        assertEquals(
                1,
                paymentTransactionRepository
                        .contarPorReferencia(referencia)
        );
    }
}
