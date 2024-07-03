package executor.service.exception;

public class ScenarioWasNotFoundException extends BusinessLogicException {
    public ScenarioWasNotFoundException(String id) {
        super("scenario with id: " + id + " was not found!");
    }
}
