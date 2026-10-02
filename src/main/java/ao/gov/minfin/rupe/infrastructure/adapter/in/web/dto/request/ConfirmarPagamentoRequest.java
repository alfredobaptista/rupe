package ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record ConfirmarPagamentoRequest(

        @NotBlank(message = "A referência do RUPE é obrigatória.")
        String referencia,

        @NotBlank(message = "O número do recibo é obrigatório.")
        String numeroRecibo,

        @NotNull(message = "A data do pagamento é obrigatória.")
        LocalDateTime dataPagamento

) {
}
