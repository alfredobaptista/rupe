package ao.gov.minfin.rupe.infrastructure.adapter.out.webhook;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.HexFormat;

public final class HmacSigner {

    private static final String ALGORITMO = "HmacSHA256";

    private HmacSigner() {
    }

    public static String assinar(String payload, String secret) {
        try {
            Mac mac = Mac.getInstance(ALGORITMO);

            mac.init(new SecretKeySpec(
                    secret.getBytes(StandardCharsets.UTF_8),
                    ALGORITMO
            ));

            byte[] hash = mac.doFinal(
                    payload.getBytes(StandardCharsets.UTF_8)
            );

            return HexFormat.of().formatHex(hash);

        } catch (Exception exception) {
            throw new IllegalStateException(
                    "Não foi possível calcular a assinatura HMAC.",
                    exception
            );
        }
    }

    public static boolean verificar(
            String payload,
            String secret,
            String assinaturaEsperada
    ) {
        String calculada = assinar(payload, secret);

        return MessageDigest.isEqual(
                calculada.getBytes(StandardCharsets.UTF_8),
                assinaturaEsperada.getBytes(StandardCharsets.UTF_8)
        );
    }
}