package executor.service.exception.source.okhttp;

public class UnsuccessfulResponseException extends OkhttpException{
    public UnsuccessfulResponseException(int code) {
        super("Request failed with status code: " + code);
    }
}
