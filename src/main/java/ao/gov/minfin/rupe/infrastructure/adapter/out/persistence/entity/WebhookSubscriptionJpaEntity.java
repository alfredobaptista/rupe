package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "webhook_subscriptions",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_webhook_subscription_nif",
                        columnNames = "nif"
                )
        }
)
public class WebhookSubscriptionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String nif;

    @Column(nullable = false, length = 500)
    private String url;

    @Column(nullable = false, length = 255)
    private String secret;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "criado_em", nullable = false, updatable = false)
    private LocalDateTime criadoEm;

    protected WebhookSubscriptionJpaEntity() {
    }

    public WebhookSubscriptionJpaEntity(
            String nif,
            String url,
            String secret,
            boolean ativo,
            LocalDateTime criadoEm
    ) {
        this.nif = nif;
        this.url = url;
        this.secret = secret;
        this.ativo = ativo;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getNif() {
        return nif;
    }

    public String getUrl() {
        return url;
    }

    public String getSecret() {
        return secret;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }
}