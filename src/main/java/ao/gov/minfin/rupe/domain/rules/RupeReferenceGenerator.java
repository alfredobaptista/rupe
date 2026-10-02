package ao.gov.minfin.rupe.domain.rules;

public final class RupeReferenceGenerator {

    private static final int TAMANHO_ORGANISMO = 4;
    private static final int TAMANHO_MODULO = 2;
    private static final int TAMANHO_SEQUENCIAL = 13;
    private static final int TAMANHO_REFERENCIA = 20;

    private static final long SEQUENCIAL_MAXIMO =
            9_999_999_999_999L;

    private RupeReferenceGenerator() {
    }

    public static String gerar(
            String codigoOrganismo,
            String codigoModulo,
            long sequencial
    ) {
        validarCodigoOrganismo(codigoOrganismo);
        validarCodigoModulo(codigoModulo);
        validarSequencial(sequencial);

        String sequencialFormatado = String.format(
                "%0" + TAMANHO_SEQUENCIAL + "d",
                sequencial
        );

        String corpo =
                codigoOrganismo
                + codigoModulo
                + sequencialFormatado;

        int digitoControlo =
                LuhnAlgorithm.calcularDigito(corpo);

        String referencia = corpo + digitoControlo;

        validarTamanhoReferencia(referencia);

        return referencia;
    }

    private static void validarCodigoOrganismo(
            String codigoOrganismo
    ) {
        if (codigoOrganismo == null
                || codigoOrganismo.length() != TAMANHO_ORGANISMO
                || !codigoOrganismo.matches("\\d+")) {

            throw new IllegalArgumentException(
                    "O código do organismo deve conter exactamente "
                    + TAMANHO_ORGANISMO
                    + " dígitos."
            );
        }
    }

    private static void validarCodigoModulo(
            String codigoModulo
    ) {
        if (codigoModulo == null
                || codigoModulo.length() != TAMANHO_MODULO
                || !codigoModulo.matches("\\d+")) {

            throw new IllegalArgumentException(
                    "O código do módulo deve conter exactamente "
                    + TAMANHO_MODULO
                    + " dígitos."
            );
        }
    }

    private static void validarSequencial(long sequencial) {
        if (sequencial < 0
                || sequencial > SEQUENCIAL_MAXIMO) {

            throw new IllegalArgumentException(
                    "O sequencial deve possuir entre 0 e "
                    + TAMANHO_SEQUENCIAL
                    + " dígitos."
            );
        }
    }

    private static void validarTamanhoReferencia(
            String referencia
    ) {
        if (referencia.length() != TAMANHO_REFERENCIA) {
            throw new IllegalStateException(
                    "A referência RUPE deve possuir exactamente "
                    + TAMANHO_REFERENCIA
                    + " dígitos."
            );
        }
    }
}