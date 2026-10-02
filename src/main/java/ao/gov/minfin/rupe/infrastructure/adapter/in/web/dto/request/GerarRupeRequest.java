package ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.request;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDateTime;

public record GerarRupeRequest(

        @NotBlank(message = "O NIF é obrigatório.")
        @Pattern(
                regexp = "\\d{9}[A-Z]{2}\\d{3}",
                message = "O NIF deve seguir o formato 009150115UE044."
        )
        String nif,

        @NotBlank(message = "O nome do contribuinte é obrigatório.")
        String nomeContribuinte,

        @NotBlank(message = "O código do serviço é obrigatório.")
        String codigoServico,

        @NotNull(message = "A data de expiração é obrigatória.")
        @Future(message = "A data de expiração deve ser futura.")
        LocalDateTime dataExpiracao

) {
}
