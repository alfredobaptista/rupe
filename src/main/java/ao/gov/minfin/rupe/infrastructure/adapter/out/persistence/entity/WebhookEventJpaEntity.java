package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(
        name = "webhook_events",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_webhook_event_event_id",
                        columnNames = "event_id"
                )
        }
)
public class WebhookEventJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "event_id", nullable = false, updatable = false)
    private UUID eventId;

    @Column(nullable = false, length = 20, updatable = false)
    private String referencia;

    @Column(name = "url_destino", nullable = false, length = 500, updatable = false)
    private String urlDestino;

    @Column(name = "secret_usado", nullable = false, length = 255, updatable = false)
    private String secretUsado;

    @Column(nullable = false, columnDefinition = "TEXT", updatable = false)
    private String payload;

    @Column(nullable = false, length = 20)
    private String estado;

    @Column(nullable = false)
    private int tentativas;

    @Column(name = "ultimo_erro", length = 500)
    private String ultimoErro;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    @Column(name = "proxima_tentativa_em", nullable = false)
    private LocalDateTime proximaTentativaEm;

    @Column(name = "processado_em")
    private LocalDateTime processadoEm;

    protected WebhookEventJpaEntity() {
    }

    public WebhookEventJpaEntity(
            UUID eventId,
            String referencia,
            String urlDestino,
            String secretUsado,
            String payload,
            String estado,
            int tentativas,
            String ultimoErro,
            LocalDateTime criadoEm,
            LocalDateTime proximaTentativaEm,
            LocalDateTime processadoEm
    ) {
        this.eventId = eventId;
        this.referencia = referencia;
        this.urlDestino = urlDestino;
        this.secretUsado = secretUsado;
        this.payload = payload;
        this.estado = estado;
        this.tentativas = tentativas;
        this.ultimoErro = ultimoErro;
        this.criadoEm = criadoEm;
        this.proximaTentativaEm = proximaTentativaEm;
        this.processadoEm = processadoEm;
    }

    public Long getId() {
        return id;
    }

    public UUID getEventId() {
        return eventId;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getUrlDestino() {
        return urlDestino;
    }

    public String getSecretUsado() {
        return secretUsado;
    }

    public String getPayload() {
        return payload;
    }

    public String getEstado() {
        return estado;
    }

    public int getTentativas() {
        return tentativas;
    }

    public String getUltimoErro() {
        return ultimoErro;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public LocalDateTime getProximaTentativaEm() {
        return proximaTentativaEm;
    }

    public LocalDateTime getProcessadoEm() {
        return processadoEm;
    }

    public void marcarEnviado(LocalDateTime processadoEm) {
        this.estado = "ENVIADO";
        this.processadoEm = processadoEm;
        this.ultimoErro = null;
    }

    public void registarFalha(
            String erro,
            LocalDateTime proximaTentativaEm,
            boolean definitivo
    ) {
        this.tentativas += 1;
        this.ultimoErro = erro != null && erro.length() > 500
                ? erro.substring(0, 500)
                : erro;
        this.proximaTentativaEm = proximaTentativaEm;

        if (definitivo) {
            this.estado = "FALHADO";
        }
    }
}