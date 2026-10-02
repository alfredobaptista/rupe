package ao.gov.minfin.rupe.domain.enums;

public enum EstadoPagamento {

    ABERTO(1, "Aberto"),
    PAGO(2, "Pago"),
    EXPIRADO(3, "Expirado"),
    CANCELADO(4, "Cancelado");

    private final int numero;
    private final String descricao;

    EstadoPagamento(int numero, String descricao) {
        this.numero = numero;
        this.descricao = descricao;
    }

    public int getNumero() {
        return numero;
    }

    public String getDescricao() {
        return descricao;
    }
}