package executor.service.model;

import jakarta.validation.constraints.NotEmpty;

public record Step(@NotEmpty String action, @NotEmpty String value) {
}