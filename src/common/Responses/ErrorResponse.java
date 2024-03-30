package common.Responses;

public class ErrorResponse extends Response{
    public ErrorResponse(String commandName, String message) {
        super(commandName, message);
    }
}
