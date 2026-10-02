package ao.gov.minfin.rupe.domain.valueobject;

import java.util.Objects;

public final class Nif {

    private final String valor;

    public Nif(String valor) {
        validar(valor);

        this.valor = valor.trim();
    }

    private void validar(String valor) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(
                    "O NIF é obrigatório."
            );
        }
    }

    public String getValor() {
        return valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Nif nif)) {
            return false;
        }

        return valor.equals(nif.valor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valor);
    }

    @Override
    public String toString() {
        return valor;
    }
}