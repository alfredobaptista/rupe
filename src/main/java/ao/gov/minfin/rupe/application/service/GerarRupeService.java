package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.command.GerarRupeCommand;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.ServicoRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Contribuinte;
import ao.gov.minfin.rupe.domain.entity.Rupe;
import ao.gov.minfin.rupe.domain.entity.Servico;
import ao.gov.minfin.rupe.domain.exception.ServicoNaoEncontradoException;
import ao.gov.minfin.rupe.domain.rules.RupeReferenceGenerator;

public class GerarRupeService implements GerarRupeUseCase {

    private final RupeRepositoryPort rupeRepository;
    private final ServicoRepositoryPort servicoRepository;

    public GerarRupeService(
            RupeRepositoryPort rupeRepository,
            ServicoRepositoryPort servicoRepository
    ) {
        this.rupeRepository = rupeRepository;
        this.servicoRepository = servicoRepository;
    }

    @Override
    public Rupe executar(GerarRupeCommand command) {

        Servico servico = servicoRepository
                .buscarPorCodigo(command.codigoServico())
                .orElseThrow(() ->
                        new ServicoNaoEncontradoException(
                                command.codigoServico()
                        )
                );

        if (!servico.isElegivelParaEmissao()) {
            throw new IllegalStateException(
                    "O serviço não está disponível para emissão."
            );
        }

        Contribuinte contribuinte = new Contribuinte(
                command.nif(),
                command.nomeContribuinte()
        );

        long sequencial =
                rupeRepository.obterProximoSequencial();

        String referencia = RupeReferenceGenerator.gerar(
                servico.getCodigoOrganismo(),
                servico.getCodigoModulo(),
                sequencial
        );

        Rupe rupe = new Rupe(
                referencia,
                contribuinte,
                servico.getCodigo(),
                servico.getNome(),
                servico.calcularValorTotal(),
                java.time.LocalDateTime.now(),
                command.dataExpiracao()
        );

        return rupeRepository.guardar(rupe);
    }
}