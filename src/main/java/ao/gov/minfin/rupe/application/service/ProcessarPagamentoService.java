package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.ProcessarPagamentoCommand;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.application.port.out.PaymentTransactionRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.WebhookOutboxPort;
import ao.gov.minfin.rupe.application.port.out.WebhookSubscriptionPort;
import ao.gov.minfin.rupe.application.webhook.WebhookPayload;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.exception.RupeNaoEncontradoException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.time.LocalDateTime;
import java.util.UUID;

public class ProcessarPagamentoService
        implements ProcessarPagamentoUseCase {

    private final RupeRepositoryPort rupeRepository;
    private final PaymentTransactionRepositoryPort paymentTransactionRepository;
    private final WebhookSubscriptionPort webhookSubscriptionPort;
    private final WebhookOutboxPort webhookOutboxPort;
    private final ObjectMapper objectMapper;

    public ProcessarPagamentoService(
            RupeRepositoryPort rupeRepository,
            PaymentTransactionRepositoryPort paymentTransactionRepository,
            WebhookSubscriptionPort webhookSubscriptionPort,
            WebhookOutboxPort webhookOutboxPort,
            ObjectMapper objectMapper
    ) {
        this.rupeRepository = rupeRepository;
        this.paymentTransactionRepository = paymentTransactionRepository;
        this.webhookSubscriptionPort = webhookSubscriptionPort;
        this.webhookOutboxPort = webhookOutboxPort;
        this.objectMapper = objectMapper;
    }

    @Override
    public Rupe executar(ProcessarPagamentoCommand command) {

        String idempotencyKey = command.idempotencyKey().trim();
        String referencia = command.referencia().trim();

        if (paymentTransactionRepository
                .existePorIdempotencyKey(idempotencyKey)) {

            return rupeRepository
                    .buscarPorReferencia(referencia)
                    .orElseThrow(() ->
                            new RupeNaoEncontradoException(referencia)
                    );
        }

        Rupe rupe = rupeRepository
                .buscarPorReferenciaComBloqueio(referencia)
                .orElseThrow(() ->
                        new RupeNaoEncontradoException(referencia)
                );

        if (paymentTransactionRepository
                .existePorIdempotencyKey(idempotencyKey)) {

            return rupeRepository
                    .buscarPorReferencia(referencia)
                    .orElseThrow(() ->
                            new RupeNaoEncontradoException(referencia)
                    );
        }

        rupe.confirmarPagamento(
                command.numeroRecibo(),
                command.dataPagamento()
        );

        Rupe rupeActualizado = rupeRepository.guardar(rupe);

        paymentTransactionRepository.registar(
                idempotencyKey,
                referencia,
                command.numeroRecibo(),
                command.dataPagamento(),
                LocalDateTime.now()
        );

        enfileirarWebhook(rupeActualizado);

        return rupeActualizado;
    }

    private void enfileirarWebhook(Rupe rupe) {

    String nif = rupe.getContribuinte().getNif().getValor();

    webhookSubscriptionPort.buscarPorNif(nif)
            .ifPresent(subscricao -> {

                UUID eventId = UUID.randomUUID();

                WebhookPayload payload =
                        WebhookPayload.from(rupe, eventId);

                String payloadJson = serializar(payload);

                webhookOutboxPort.enfileirar(
                        eventId,
                        rupe.getReferencia(),
                        subscricao.url(),
                        subscricao.secret(),
                        payloadJson
                );
            });
}

    private String serializar(WebhookPayload payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException exception) {
            throw new IllegalStateException(
                    "Não foi possível serializar o payload do webhook.",
                    exception
            );
        }
    }
}