package ao.gov.minfin.rupe.domain.rules;

public final class LuhnAlgorithm {

    private LuhnAlgorithm() {
    }

    public static int calcularDigito(String numero) {
        validarNumero(numero);

        int soma = 0;
        boolean duplicar = true;

        for (int i = numero.length() - 1; i >= 0; i--) {
            int digito = Character.digit(numero.charAt(i), 10);

            if (duplicar) {
                digito *= 2;

                if (digito > 9) {
                    digito -= 9;
                }
            }

            soma += digito;
            duplicar = !duplicar;
        }

        return (10 - (soma % 10)) % 10;
    }

    public static boolean validar(String numeroComDigito) {
        if (numeroComDigito == null
                || numeroComDigito.isBlank()
                || !numeroComDigito.matches("\\d+")
                || numeroComDigito.length() < 2) {
            return false;
        }

        String corpo = numeroComDigito.substring(
                0,
                numeroComDigito.length() - 1
        );

        int digitoInformado = Character.digit(
                numeroComDigito.charAt(numeroComDigito.length() - 1),
                10
        );

        return calcularDigito(corpo) == digitoInformado;
    }

    private static void validarNumero(String numero) {
        if (numero == null || numero.isBlank()) {
            throw new IllegalArgumentException(
                    "O número para cálculo do dígito de controlo é obrigatório."
            );
        }

        if (!numero.matches("\\d+")) {
            throw new IllegalArgumentException(
                    "O número deve conter apenas dígitos."
            );
        }
    }
}