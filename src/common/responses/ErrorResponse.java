package common.responses;

public class ErrorResponse extends Response{
    public ErrorResponse(String commandName, String message) {
        super(commandName, message);
    }
}
