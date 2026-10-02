package ao.gov.minfin.rupe.domain.valueobject;

import java.math.BigDecimal;
import java.util.Objects;

public final class Emolumento {

    private final String nome;
    private final BigDecimal valor;

    public Emolumento(String nome, BigDecimal valor) {
        validarNome(nome);
        validarValor(valor);

        this.nome = nome.trim();
        this.valor = valor;
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do emolumento é obrigatório."
            );
        }
    }

    private void validarValor(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException(
                    "O valor do emolumento deve ser superior a zero."
            );
        }
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Emolumento that)) {
            return false;
        }

        return nome.equals(that.nome)
                && valor.compareTo(that.valor) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, valor.stripTrailingZeros());
    }

    @Override
    public String toString() {
        return nome + " - " + valor;
    }
}