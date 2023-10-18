package executor.service.exception.source.okhttp;

public class EmptyResponseBodyException extends OkhttpException {
    public EmptyResponseBodyException() {
        super("Response body is empty");
    }
}
