package executor.service.model;

import java.time.LocalDateTime;

public record ErrorResponseDto(String errorMessage, LocalDateTime errorTime) {
}