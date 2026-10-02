package ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response;

import ao.gov.minfin.rupe.domain.entity.Servico;
import ao.gov.minfin.rupe.domain.valueobject.Emolumento;

import java.math.BigDecimal;
import java.util.List;

public record ServicoResponse(
        String codigo,
        String nome,
        List<EmolumentoResponse> emolumentos
) {

    public static ServicoResponse from(Servico servico) {
        return new ServicoResponse(
                servico.getCodigo(),
                servico.getNome(),
                servico.getEmolumentos()
                        .stream()
                        .map(EmolumentoResponse::from)
                        .toList()
        );
    }

    public record EmolumentoResponse(
            String nome,
            BigDecimal valor
    ) {

        public static EmolumentoResponse from(Emolumento emolumento) {
            return new EmolumentoResponse(
                    emolumento.getNome(),
                    emolumento.getValor()
            );
        }
    }
}