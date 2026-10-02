package ao.gov.minfin.rupe.application.command;

import ao.gov.minfin.rupe.domain.valueobject.Nif;

import java.time.LocalDateTime;
import java.util.Objects;

public record GerarRupeCommand(
        Nif nif,
        String nomeContribuinte,
        String codigoServico,
        LocalDateTime dataExpiracao
) {

    public GerarRupeCommand {
        Objects.requireNonNull(
                nif,
                "O NIF é obrigatório."
        );

        if (nomeContribuinte == null || nomeContribuinte.isBlank()) {
            throw new IllegalArgumentException(
                    "O nome do contribuinte é obrigatório."
            );
        }

        if (codigoServico == null || codigoServico.isBlank()) {
            throw new IllegalArgumentException(
                    "O código do serviço é obrigatório."
            );

        }

        Objects.requireNonNull(
                dataExpiracao,
                "A data de expiração é obrigatória."
        );
    }
}