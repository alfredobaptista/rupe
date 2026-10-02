package ao.gov.minfin.rupe.application.service;

import ao.gov.minfin.rupe.application.port.in.ConsultarServicosUseCase;
import ao.gov.minfin.rupe.application.port.out.ServicoRepositoryPort;
import ao.gov.minfin.rupe.domain.entity.Servico;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultarServicosService implements ConsultarServicosUseCase {

    private final ServicoRepositoryPort servicoRepository;

    public ConsultarServicosService(ServicoRepositoryPort servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    @Override
    public List<Servico> executar() {
        return servicoRepository.listarTodos();
    }
}