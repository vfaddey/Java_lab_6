package server.exceptions;

public class RecursionExecutionException extends RuntimeException{
    public RecursionExecutionException(String message) {
        super(message);
    }
}
