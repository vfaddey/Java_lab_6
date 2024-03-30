package client.managers;

import common.Responses.Response;

public class ResponseHandler {
    private final ConsoleHandler consoleHandler;

    public ResponseHandler(ConsoleHandler consoleHandler) {
        this.consoleHandler = consoleHandler;
    }

    public void handleResponse(Response response) {
        consoleHandler.println(response.getMessage());
    }
}
