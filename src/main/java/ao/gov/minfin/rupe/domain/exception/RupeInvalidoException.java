package ao.gov.minfin.rupe.domain.exception;

public class RupeInvalidoException extends RuntimeException {

    public RupeInvalidoException(String mensagem) {
        super(mensagem);
    }
}