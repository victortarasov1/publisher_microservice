package executor.service.logger.aspect;

enum LogMessage {
    EXECUTING_METHOD("Executing method {} in class {} with arguments {}"),
    METHOD_EXECUTION_COMPLETED("Method {} execution completed in class {}"),
    METHOD_RETURN_VALUE("Method {} in class {} returned value: {}");

    private final String message;

    LogMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
