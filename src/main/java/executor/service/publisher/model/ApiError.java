package executor.service.publisher.model;

import java.util.List;

public record ApiError(String message, List<String> debugMessage) {
}