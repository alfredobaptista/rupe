package ao.gov.minfin.rupe.domain.exception;

public class TransacaoDuplicadaException extends RuntimeException {

    public TransacaoDuplicadaException(String mensagem) {
        super(mensagem);
    }
}