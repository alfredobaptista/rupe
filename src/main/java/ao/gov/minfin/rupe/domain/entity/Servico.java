package ao.gov.minfin.rupe.domain.entity;

import ao.gov.minfin.rupe.domain.valueobject.Emolumento;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class Servico {

    private final String codigo;
    private final String nome;
    private final String codigoOrganismo;
    private final String codigoModulo;
    private final boolean ativo;
    private final List<Emolumento> emolumentos;

    public Servico(
            String codigo,
            String nome,
            String codigoOrganismo,
            String codigoModulo,
            boolean ativo,
            List<Emolumento> emolumentos
    ) {
        validarCodigo(codigo);
        validarNome(nome);
        validarCodigoOrganismo(codigoOrganismo);
        validarCodigoModulo(codigoModulo);

        Objects.requireNonNull(
                emolumentos,
                "A lista de emolumentos é obrigatória."
        );

        if (emolumentos.isEmpty()) {
            throw new IllegalArgumentException(
                    "O serviço deve possuir pelo menos um emolumento."
            );
        }

        if (emolumentos.stream().anyMatch(Objects::isNull)) {
            throw new IllegalArgumentException(
                    "A lista de emolumentos não pode conter valores nulos."
            );
        }

        this.codigo = codigo.trim();
        this.nome = nome.trim();
        this.codigoOrganismo = codigoOrganismo.trim();
        this.codigoModulo = codigoModulo.trim();
        this.ativo = ativo;
        this.emolumentos = List.copyOf(emolumentos);
    }

    public BigDecimal calcularValorTotal() {
        return emolumentos.stream()
                .map(Emolumento::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public boolean isElegivelParaEmissao() {
        return ativo
                && calcularValorTotal().compareTo(BigDecimal.ZERO) > 0;
    }

    private void validarCodigo(String codigo) {
        if (codigo == null || codigo.isBlank()) {
            throw new IllegalArgumentException(
                    "O código do serviço é obrigatório."
            );
        }
    }

    private void validarNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do serviço é obrigatório."
            );
        }
    }

    private void validarCodigoOrganismo(String codigoOrganismo) {
        if (codigoOrganismo == null
                || !codigoOrganismo.matches("\\d{4}")) {
            throw new IllegalArgumentException(
                    "O código do organismo deve conter exactamente 4 dígitos."
            );
        }
    }

    private void validarCodigoModulo(String codigoModulo) {
        if (codigoModulo == null
                || !codigoModulo.matches("\\d{2}")) {
            throw new IllegalArgumentException(
                    "O código do módulo deve conter exactamente 2 dígitos."
            );
        }
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigoOrganismo() {
        return codigoOrganismo;
    }

    public String getCodigoModulo() {
        return codigoModulo;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public List<Emolumento> getEmolumentos() {
        return emolumentos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Servico servico)) {
            return false;
        }

        return codigo.equals(servico.codigo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(codigo);
    }
}