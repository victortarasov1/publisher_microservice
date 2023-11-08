package executor.service.source.exception.okhttp;

public class UnsuccessfulResponseException extends OkhttpException{
    public UnsuccessfulResponseException(int code) {
        super("Request failed with status code: " + code);
    }
}
