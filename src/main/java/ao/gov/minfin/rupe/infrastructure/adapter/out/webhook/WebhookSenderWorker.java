package ao.gov.minfin.rupe.infrastructure.adapter.out.webhook;

import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity.WebhookEventJpaEntity;
import ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.repository.SpringDataWebhookEventRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClient;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class WebhookSenderWorker {

    private static final Logger LOG =
            LoggerFactory.getLogger(WebhookSenderWorker.class);

    private static final int MAX_TENTATIVAS = 5;
    private static final Duration BACKOFF_BASE = Duration.ofSeconds(30);
    private static final int LOTE = 50;

    private final SpringDataWebhookEventRepository repository;
    private final RestClient restClient;

    public WebhookSenderWorker(
            SpringDataWebhookEventRepository repository,
            RestClient restClient
    ) {
        this.repository = repository;
        this.restClient = restClient;
    }

    @Scheduled(fixedDelayString = "30000")
    @Transactional
    public void processarPendentes() {

        List<WebhookEventJpaEntity> pendentes =
                repository.buscarPendentes(
                        LocalDateTime.now(),
                        PageRequest.of(0, LOTE)
                );

        if (pendentes.isEmpty()) {
            return;
        }

        LOG.info("Webhook: {} eventos pendentes.", pendentes.size());

        for (WebhookEventJpaEntity evento : pendentes) {
            enviar(evento);
        }
    }

    private void enviar(WebhookEventJpaEntity evento) {

        try {
            String assinatura = HmacSigner.assinar(
                    evento.getPayload(),
                    evento.getSecretUsado()
            );

            restClient.post()
                    .uri(evento.getUrlDestino())
                    .header("Content-Type", "application/json")
                    .header("X-RUPE-Signature", "sha256=" + assinatura)
                    .header(
                            "X-RUPE-Event-Id",
                            evento.getEventId().toString()
                    )
                    .body(evento.getPayload())
                    .retrieve()
                    .toBodilessEntity();

            evento.marcarEnviado(LocalDateTime.now());

            LOG.info(
                    "Webhook enviado: referencia={}, eventId={}",
                    evento.getReferencia(),
                    evento.getEventId()
            );

        } catch (Exception exception) {

            int tentativas = evento.getTentativas() + 1;

            boolean definitivo = tentativas >= MAX_TENTATIVAS;

            LocalDateTime proxima = LocalDateTime.now().plusSeconds(
                    BACKOFF_BASE.getSeconds() * (1L << (tentativas - 1))
            );

            evento.registarFalha(
                    exception.getMessage(),
                    proxima,
                    definitivo
            );

            LOG.warn(
                    "Falha ao enviar webhook: referencia={}, "
                    + "tentativa={}, definitivo={}, erro={}",
                    evento.getReferencia(),
                    tentativas,
                    definitivo,
                    exception.getMessage()
            );
        }
    }
}
