package ao.gov.minfin.rupe.domain.entity;

import ao.gov.minfin.rupe.domain.enums.EstadoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Rupe {

    private final String referencia;
    private final Contribuinte contribuinte;
    private final String codigoServico;
    private final String descricaoServico;
    private final BigDecimal valor;
    private final LocalDateTime dataEmissao;
    private final LocalDateTime dataExpiracao;

    private EstadoPagamento estado;
    private String numeroRecibo;
    private LocalDateTime dataPagamento;

    public Rupe(
            String referencia,
            Contribuinte contribuinte,
            String codigoServico,
            String descricaoServico,
            BigDecimal valor,
            LocalDateTime dataEmissao,
            LocalDateTime dataExpiracao
    ) {
        validarReferencia(referencia);
        validarCodigoServico(codigoServico);
        validarDescricaoServico(descricaoServico);
        validarValor(valor);

        this.contribuinte = Objects.requireNonNull(
                contribuinte,
                "O contribuinte é obrigatório."
        );

        this.dataEmissao = Objects.requireNonNull(
                dataEmissao,
                "A data de emissão é obrigatória."
        );

        this.dataExpiracao = Objects.requireNonNull(
                dataExpiracao,
                "A data de expiração é obrigatória."
        );

        if (!dataExpiracao.isAfter(dataEmissao)) {
            throw new IllegalArgumentException(
                    "A data de expiração deve ser posterior à data de emissão."
            );
        }

        this.referencia = referencia.trim();
        this.codigoServico = codigoServico.trim();
        this.descricaoServico = descricaoServico.trim();
        this.valor = valor;
        this.estado = EstadoPagamento.ABERTO;
    }

    public void confirmarPagamento(
            String numeroRecibo,
            LocalDateTime dataPagamento
    ) {
        if (estado != EstadoPagamento.ABERTO) {
            throw new IllegalStateException(
                    "Apenas um RUPE aberto pode ser pago."
            );
        }

        if (numeroRecibo == null || numeroRecibo.isBlank()) {
            throw new IllegalArgumentException(
                    "O número do recibo é obrigatório."
            );
        }

        this.dataPagamento = Objects.requireNonNull(
                dataPagamento,
                "A data do pagamento é obrigatória."
        );

        this.numeroRecibo = numeroRecibo.trim();
        this.estado = EstadoPagamento.PAGO;
    }

    public void expirar() {
        if (estado != EstadoPagamento.ABERTO) {
            return;
        }

        this.estado = EstadoPagamento.EXPIRADO;
    }

    public void cancelar() {
        if (estado != EstadoPagamento.ABERTO) {
            throw new IllegalStateException(
                    "Apenas um RUPE aberto pode ser cancelado."
            );
        }

        this.estado = EstadoPagamento.CANCELADO;
    }

    private void validarReferencia(String referencia) {
        if (referencia == null || referencia.isBlank()) {
            throw new IllegalArgumentException(
                    "A referência RUPE é obrigatória."
            );
        }
    }

    private void validarCodigoServico(String codigoServico) {
        if (codigoServico == null || codigoServico.isBlank()) {
            throw new IllegalArgumentException(
                    "O código do serviço é obrigatório."
            );
        }
    }

    private void validarDescricaoServico(String descricaoServico) {
        if (descricaoServico == null || descricaoServico.isBlank()) {
            throw new IllegalArgumentException(
                    "A descrição do serviço é obrigatória."
            );
        }
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do RUPE deve ser superior a zero."
            );
        }
    }

    public String getReferencia() {
        return referencia;
    }

    public Contribuinte getContribuinte() {
        return contribuinte;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Rupe rupe)) {
            return false;
        }

        return referencia.equals(rupe.referencia);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referencia);
    }
}