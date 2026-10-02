package ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response;

import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.enums.EstadoPagamento;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ConsultaRupeResponse(
        String referencia,
        String nomeContribuinte,
        String codigoServico,
        String descricaoServico,
        BigDecimal valor,
        LocalDateTime dataEmissao,
        LocalDateTime dataExpiracao,
        EstadoPagamento estado,
        String numeroRecibo,
        LocalDateTime dataPagamento
) {

    public static ConsultaRupeResponse from(Rupe rupe) {
        return new ConsultaRupeResponse(
                rupe.getReferencia(),
                rupe.getContribuinte().getNome(),
                rupe.getCodigoServico(),
                rupe.getDescricaoServico(),
                rupe.getValor(),
                rupe.getDataEmissao(),
                rupe.getDataExpiracao(),
                rupe.getEstado(),
                rupe.getNumeroRecibo(),
                rupe.getDataPagamento()
        );
    }
}