package ao.gov.minfin.rupe.infrastructure.config;

import ao.gov.minfin.rupe.application.port.in.ConfirmarBaixaUseCase;
import ao.gov.minfin.rupe.application.port.in.ConsultarRupeUseCase;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.ServicoRepositoryPort;
import ao.gov.minfin.rupe.application.service.ConfirmarBaixaService;
import ao.gov.minfin.rupe.application.service.ConsultarRupeService;
import ao.gov.minfin.rupe.application.service.GerarRupeService;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.application.port.out.PaymentTransactionRepositoryPort;
import ao.gov.minfin.rupe.application.service.ProcessarPagamentoService;
import ao.gov.minfin.rupe.infrastructure.adapter.in.transaction.TransactionalProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.infrastructure.adapter.in.transaction.TransactionalConfirmarBaixaUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public GerarRupeUseCase gerarRupeUseCase(
            RupeRepositoryPort rupeRepository,
            ServicoRepositoryPort servicoRepository
    ) {
        return new GerarRupeService(
                rupeRepository,
                servicoRepository
        );
    }

    @Bean
    public ConsultarRupeUseCase consultarRupeUseCase(
            RupeRepositoryPort rupeRepository
    ) {
        return new ConsultarRupeService(
                rupeRepository
        );
    }

    @Bean
    public ConfirmarBaixaUseCase confirmarBaixaUseCase(
            RupeRepositoryPort rupeRepository
    ) {
        ConfirmarBaixaUseCase service =
                new ConfirmarBaixaService(
                        rupeRepository
                );

        return new TransactionalConfirmarBaixaUseCase(
                service
        );
    }


    @Bean
public ProcessarPagamentoUseCase processarPagamentoUseCase(
        RupeRepositoryPort rupeRepository,
        PaymentTransactionRepositoryPort
                paymentTransactionRepository
) {
    ProcessarPagamentoUseCase service =
            new ProcessarPagamentoService(
                    rupeRepository,
                    paymentTransactionRepository
            );

    return new TransactionalProcessarPagamentoUseCase(
            service
    );
}
}