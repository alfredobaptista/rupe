package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(
        name = "payment_transactions",
        uniqueConstraints = {
          @UniqueConstraint(
                        name = "uk_payment_transaction_idempotency_key",
                        columnNames = "idempotency_key"
                )
        }
)
public class PaymentTransactionJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "idempotency_key",
            nullable = false,
            length = 100,
            updatable = false
    )
    private String idempotencyKey;

    @Column(
            nullable = false,
            length = 20,
            updatable = false
    )
    private String referencia;

    @Column(
            name = "numero_recibo",
            nullable = false,
            length = 100,
            updatable = false
    )
    private String numeroRecibo;

    @Column(
            name = "data_pagamento",
            nullable = false,
            updatable = false
    )
    private LocalDateTime dataPagamento;

    @Column(
            name = "processado_em",
            nullable = false,
            updatable = false
    )
    private LocalDateTime processadoEm;

    protected PaymentTransactionJpaEntity() {
    }

    public PaymentTransactionJpaEntity(
            String idempotencyKey,
            String referencia,
            String numeroRecibo,
            LocalDateTime dataPagamento,
            LocalDateTime processadoEm
    ) {
        this.idempotencyKey = idempotencyKey;
        this.referencia = referencia;
        this.numeroRecibo = numeroRecibo;
        this.dataPagamento = dataPagamento;
        this.processadoEm = processadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getIdempotencyKey() {
        return idempotencyKey;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public LocalDateTime getProcessadoEm() {
        return processadoEm;
    }
}