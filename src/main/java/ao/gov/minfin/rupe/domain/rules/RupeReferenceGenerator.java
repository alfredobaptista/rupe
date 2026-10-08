package ao.gov.minfin.rupe.domain.rules;

public final class RupeReferenceGenerator {

    private static final String PREFIXO_AGT = "6020";
    private static final int TAMANHO_PREFIXO = 4;
    private static final int TAMANHO_CODIGO_SERVICO = 4;
    private static final int TAMANHO_SEQUENCIAL = 11;
    private static final int TAMANHO_REFERENCIA = 20;

    private static final long SEQUENCIAL_MAXIMO = 99_999_999_999L;

    private RupeReferenceGenerator() {
    }

    public static String gerar(
            String codigoServico,
            long sequencial
    ) {
        validarCodigoServico(codigoServico);
        validarSequencial(sequencial);

        String codigoServicoFormatado = String.format(
                "%0" + TAMANHO_CODIGO_SERVICO + "d",
                Long.parseLong(codigoServico)
        );

        String sequencialFormatado = String.format(
                "%0" + TAMANHO_SEQUENCIAL + "d",
                sequencial
        );

        String corpo = PREFIXO_AGT
                + codigoServicoFormatado
                + sequencialFormatado;

        int digitoControlo = LuhnAlgorithm.calcularDigito(corpo);

        String referencia = corpo + digitoControlo;

        validarTamanhoReferencia(referencia);

        return referencia;
    }

    private static void validarCodigoServico(String codigoServico) {
        if (codigoServico == null
                || !codigoServico.matches("\\d{1,4}")) {
            throw new IllegalArgumentException(
                    "O código do serviço deve conter entre 1 e 4 dígitos."
            );
        }
    }

    private static void validarSequencial(long sequencial) {
        if (sequencial < 0 || sequencial > SEQUENCIAL_MAXIMO) {
            throw new IllegalArgumentException(
                    "O sequencial deve possuir no máximo "
                    + TAMANHO_SEQUENCIAL + " dígitos."
            );
        }
    }

    private static void validarTamanhoReferencia(String referencia) {
        if (referencia.length() != TAMANHO_REFERENCIA) {
            throw new IllegalStateException(
                    "A referência RUPE deve possuir exactamente "
                    + TAMANHO_REFERENCIA + " dígitos."
            );
        }
    }

    /**
     * Formata a referência para apresentação visual:
     * "60201234567890123456" -> "6020 1234 5678 9012 3456"
     */
    public static String formatar(String referencia) {
        if (referencia == null || referencia.length() != TAMANHO_REFERENCIA) {
            throw new IllegalArgumentException(
                    "A referência deve possuir 20 dígitos."
            );
        }

        return referencia.substring(0, 4)
                + " "
                + referencia.substring(4, 8)
                + " "
                + referencia.substring(8, 12)
                + " "
                + referencia.substring(12, 16)
                + " "
                + referencia.substring(16, 20);
    }
}