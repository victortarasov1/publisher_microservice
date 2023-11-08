package executor.service.source.exception.okhttp;

public class EmptyResponseBodyException extends OkhttpException {
    public EmptyResponseBodyException() {
        super("Response body is empty");
    }
}
