package ao.gov.minfin.rupe.infrastructure.config;

import ao.gov.minfin.rupe.application.port.in.ConfirmarBaixaUseCase;
import ao.gov.minfin.rupe.application.port.in.ConsultarRupeUseCase;
import ao.gov.minfin.rupe.application.port.in.ConsultarServicosUseCase;
import ao.gov.minfin.rupe.application.port.in.GerarRupeUseCase;
import ao.gov.minfin.rupe.application.port.in.ProcessarPagamentoUseCase;
import ao.gov.minfin.rupe.application.port.out.PaymentTransactionRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.RupeRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.ServicoRepositoryPort;
import ao.gov.minfin.rupe.application.port.out.WebhookOutboxPort;
import ao.gov.minfin.rupe.application.port.out.WebhookSubscriptionPort;
import ao.gov.minfin.rupe.application.service.ConfirmarBaixaService;
import ao.gov.minfin.rupe.application.service.ConsultarRupeService;
import ao.gov.minfin.rupe.application.service.GerarRupeService;
import ao.gov.minfin.rupe.application.service.ProcessarPagamentoService;
import ao.gov.minfin.rupe.infrastructure.adapter.in.transaction.TransactionalConfirmarBaixaUseCase;
import ao.gov.minfin.rupe.infrastructure.adapter.in.transaction.TransactionalProcessarPagamentoUseCase;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class ApplicationBeansConfig {

    // ------------------------------------------------------------
    // Infraestrutura partilhada
    // ------------------------------------------------------------

    @Bean
    public ObjectMapper objectMapper() {
        return JsonMapper.builder()
                .addModule(new JavaTimeModule())
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .build();
    }

    @Bean
    public RestClient restClient() {
        return RestClient.builder().build();
    }

    // ------------------------------------------------------------
    // Use cases
    // ------------------------------------------------------------

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
        return new ConsultarRupeService(rupeRepository);
    }

    @Bean
    public ConfirmarBaixaUseCase confirmarBaixaUseCase(
            RupeRepositoryPort rupeRepository
    ) {
        return new TransactionalConfirmarBaixaUseCase(
                new ConfirmarBaixaService(rupeRepository)
        );
    }

    @Bean
    public ProcessarPagamentoUseCase processarPagamentoUseCase(
            RupeRepositoryPort rupeRepository,
            PaymentTransactionRepositoryPort paymentTransactionRepository,
            WebhookSubscriptionPort webhookSubscriptionPort,
            WebhookOutboxPort webhookOutboxPort,
            ObjectMapper objectMapper
    ) {
        return new TransactionalProcessarPagamentoUseCase(
                new ProcessarPagamentoService(
                        rupeRepository,
                        paymentTransactionRepository,
                        webhookSubscriptionPort,
                        webhookOutboxPort,
                        objectMapper
                )
        );
    }
}