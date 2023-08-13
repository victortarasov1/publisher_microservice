package executor.service.publisher.controller.handler;

import executor.service.publisher.dto.ApiError;
import executor.service.publisher.exception.source.SourceException;
import executor.service.publisher.exception.validator.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(SourceException.class)
    protected ResponseEntity<ApiError> handleSourceExceptions(SourceException ex) {
        List<String> debugMessages = ex.getCause() != null ? List.of(ex.getCause().getMessage()) : List.of();
        ApiError apiError = new ApiError(ex.getMessage(), debugMessages);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    protected ResponseEntity<ApiError> handleValidationExceptions(ValidationException ex) {
        List<String> debugMessages = ex.getCause() != null ? List.of(ex.getCause().getMessage()) : List.of();
        ApiError apiError = new ApiError(ex.getMessage(), debugMessages);
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    protected ResponseEntity<ApiError> handleHttpMessageNotReadable(HttpMessageNotReadableException ex) {
        ApiError apiError = new ApiError("Malformed JSON Request", List.of(ex.getMessage()));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    protected ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        List<String> debugMessages = ex.getRequiredType() != null ? List.of(ex.getRequiredType().getSimpleName()) : List.of();
        ApiError apiError = new ApiError("bad argument type",
                List.of(String.format("The parameter '%s' of value '%s' could not be converted to type '%s'",
                        ex.getName(), ex.getValue(), debugMessages)));
        return new ResponseEntity<>(apiError, HttpStatus.BAD_REQUEST);
    }
}
