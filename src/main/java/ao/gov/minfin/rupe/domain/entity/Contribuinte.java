package ao.gov.minfin.rupe.domain.entity;

import ao.gov.minfin.rupe.domain.valueobject.Nif;

import java.util.Objects;

public class Contribuinte {

    private final Nif nif;
    private final String nome;

    public Contribuinte(Nif nif, String nome) {
        this.nif = Objects.requireNonNull(
                nif,
                "O NIF do contribuinte é obrigatório."
        );

        validarNome(nome);

        this.nome = nome.trim();
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do contribuinte é obrigatório."
            );
        }
    }

    public Nif getNif() {
        return nif;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Contribuinte that)) {
            return false;
        }

        return nif.equals(that.nif);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nif);
    }
}