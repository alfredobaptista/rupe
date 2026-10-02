package ao.gov.minfin.rupe.infrastructure.adapter.out.persistence.entity;

import ao.gov.minfin.rupe.domain.enums.EstadoPagamento;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "rupes",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_rupe_referencia",
                        columnNames = "referencia"
                )
        }
)
public class RupeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    @Column(
            nullable = false
    )
    private Long version;

    @Column(
            nullable = false,
            length = 20,
            updatable = false
    )
    private String referencia;

    @Column(
            name = "nif_contribuinte",
            nullable = false,
            length = 50,
            updatable = false
    )
    private String nifContribuinte;

    @Column(
            name = "nome_contribuinte",
            nullable = false,
            length = 200,
            updatable = false
    )
    private String nomeContribuinte;

    @Column(
            name = "codigo_servico",
            nullable = false,
            length = 50,
            updatable = false
    )
    private String codigoServico;

    @Column(
            name = "descricao_servico",
            nullable = false,
            length = 200,
            updatable = false
    )
    private String descricaoServico;

    @Column(
            nullable = false,
            precision = 19,
            scale = 2
    )
    private BigDecimal valor;

    @Column(
            name = "data_emissao",
            nullable = false,
            updatable = false
    )
    private LocalDateTime dataEmissao;

    @Column(
            name = "data_expiracao",
            nullable = false
    )
    private LocalDateTime dataExpiracao;

    @Enumerated(EnumType.STRING)
    @Column(
            nullable = false,
            length = 20
    )
    private EstadoPagamento estado;

    @Column(
            name = "numero_recibo",
            length = 100
    )
    private String numeroRecibo;

    @Column(
            name = "data_pagamento"
    )
    private LocalDateTime dataPagamento;

    protected RupeJpaEntity() {
    }

    public RupeJpaEntity(
            String referencia,
            String nifContribuinte,
            String nomeContribuinte,
            String codigoServico,
            String descricaoServico,
            BigDecimal valor,
            LocalDateTime dataEmissao,
            LocalDateTime dataExpiracao,
            EstadoPagamento estado,
            String numeroRecibo,
            LocalDateTime dataPagamento
    ) {
        this.referencia = referencia;
        this.nifContribuinte = nifContribuinte;
        this.nomeContribuinte = nomeContribuinte;
        this.codigoServico = codigoServico;
        this.descricaoServico = descricaoServico;
        this.valor = valor;
        this.dataEmissao = dataEmissao;
        this.dataExpiracao = dataExpiracao;
        this.estado = estado;
        this.numeroRecibo = numeroRecibo;
        this.dataPagamento = dataPagamento;
    }

    public Long getId() {
        return id;
    }

    public Long getVersion() {
        return version;
    }

    public String getReferencia() {
        return referencia;
    }

    public String getNifContribuinte() {
        return nifContribuinte;
    }

    public String getNomeContribuinte() {
        return nomeContribuinte;
    }

    public String getCodigoServico() {
        return codigoServico;
    }

    public String getDescricaoServico() {
        return descricaoServico;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public LocalDateTime getDataEmissao() {
        return dataEmissao;
    }

    public LocalDateTime getDataExpiracao() {
        return dataExpiracao;
    }

    public EstadoPagamento getEstado() {
        return estado;
    }

    public String getNumeroRecibo() {
        return numeroRecibo;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void actualizarPagamento(
            EstadoPagamento estado,
            String numeroRecibo,
            LocalDateTime dataPagamento
    ) {
        this.estado = estado;
        this.numeroRecibo = numeroRecibo;
        this.dataPagamento = dataPagamento;
    }
}