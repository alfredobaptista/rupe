package ao.gov.minfin.rupe.domain.exception;

public class ServicoNaoEncontradoException extends RuntimeException {

    public ServicoNaoEncontradoException(String codigoServico) {
        super("Serviço não encontrado: " + codigoServico);
    }
}