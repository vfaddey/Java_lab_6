package client.exceptions;

public class NoConnectionException extends RuntimeException{
    public NoConnectionException(String message) {
        super(message);
    }
}
