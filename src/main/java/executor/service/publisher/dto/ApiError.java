package executor.service.publisher.dto;

import java.util.List;

public record ApiError(String message, List<String> debugMessage) {
}