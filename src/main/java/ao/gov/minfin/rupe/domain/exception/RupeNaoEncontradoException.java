package ao.gov.minfin.rupe.domain.exception;

public class RupeNaoEncontradoException extends RuntimeException {

    public RupeNaoEncontradoException(String referencia) {
        super("RUPE não encontrado: " + referencia);
    }
}