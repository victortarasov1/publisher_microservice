package executor.service.publisher.exception.api;

public class EndpointException extends ProxyApiException {

    public EndpointException() {
        super("Incorrect API endpoint");
    }

}
