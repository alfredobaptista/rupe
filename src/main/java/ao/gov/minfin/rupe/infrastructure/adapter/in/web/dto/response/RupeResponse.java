package ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.enums.EstadoPagamento;

public record RupeResponse(
        String referencia,
        String nif,
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

    public static RupeResponse from(Rupe rupe) {
        return new RupeResponse(
                rupe.getReferencia(),
                rupe.getContribuinte().getNif().getValor(),
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