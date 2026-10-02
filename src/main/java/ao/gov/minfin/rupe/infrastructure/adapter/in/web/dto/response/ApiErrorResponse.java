package ao.gov.minfin.rupe.infrastructure.adapter.in.web.dto.response;

import java.time.LocalDateTime;
import java.util.Map;

public record ApiErrorResponse(
        int status,
        String error,
        String message,
        Map<String, String> fields,
        LocalDateTime timestamp
) {
}