package ao.gov.minfin.rupe.infrastructure.adapter.in.web.handler;

import ao.gov.minfin.rupe.domain.exception.RupeInvalidoException;
import ao.gov.minfin.rupe.domain.exception.RupeNaoEncontradoException;
import ao.gov.minfin.rupe.domain.exception.ServicoNaoEncontradoException;
import ao.gov.minfin.rupe.domain.exception.TransacaoDuplicadaException;
import ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RupeNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleRupeNaoEncontrado(
            RupeNaoEncontradoException exception
    ) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                "RUPE_NOT_FOUND",
                exception.getMessage()
        );
    }

    @ExceptionHandler(ServicoNaoEncontradoException.class)
    public ResponseEntity<ApiErrorResponse> handleServicoNaoEncontrado(
            ServicoNaoEncontradoException exception
    ) {
        return buildResponse(
                HttpStatus.NOT_FOUND,
                "SERVICO_NOT_FOUND",
                exception.getMessage()
        );
    }

    @ExceptionHandler(RupeInvalidoException.class)
    public ResponseEntity<ApiErrorResponse> handleRupeInvalido(
            RupeInvalidoException exception
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "INVALID_RUPE",
                exception.getMessage()
        );
    }

    @ExceptionHandler(TransacaoDuplicadaException.class)
    public ResponseEntity<ApiErrorResponse> handleTransacaoDuplicada(
            TransacaoDuplicadaException exception
    ) {
        return buildResponse(
                HttpStatus.CONFLICT,
                "DUPLICATE_TRANSACTION",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorResponse> handleArgumentoInvalido(
            IllegalArgumentException exception
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "INVALID_REQUEST",
                exception.getMessage()
        );
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiErrorResponse> handleEstadoInvalido(
            IllegalStateException exception
    ) {
        return buildResponse(
                HttpStatus.CONFLICT,
                "INVALID_STATE",
                exception.getMessage()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception
    ) {
        Map<String, String> fields =
                exception.getBindingResult()
                        .getFieldErrors()
                        .stream()
                        .collect(Collectors.toMap(
                                fieldError -> fieldError.getField(),
                                fieldError -> fieldError.getDefaultMessage(),
                                (first, second) -> first
                        ));

        ApiErrorResponse response =
                new ApiErrorResponse(
                        HttpStatus.BAD_REQUEST.value(),
                        "VALIDATION_ERROR",
                        "Existem campos inválidos.",
                        fields,
                        LocalDateTime.now()
                );

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ApiErrorResponse> handleJsonInvalido(
            HttpMessageNotReadableException exception
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "INVALID_JSON",
                "O corpo da requisição contém um JSON inválido."
        );
    }

    @ExceptionHandler(MissingRequestHeaderException.class)
    public ResponseEntity<ApiErrorResponse> handleHeaderAusente(
            MissingRequestHeaderException exception
    ) {
        return buildResponse(
                HttpStatus.BAD_REQUEST,
                "MISSING_HEADER",
                "O header '"
                        + exception.getHeaderName()
                        + "' é obrigatório."
        );
    }

    private ResponseEntity<ApiErrorResponse> buildResponse(
            HttpStatus status,
            String error,
            String message
    ) {
        ApiErrorResponse response =
                new ApiErrorResponse(
                        status.value(),
                        error,
                        message,
                        null,
                        LocalDateTime.now()
                );

        return ResponseEntity
                .status(status)
                .body(response);
    }
}