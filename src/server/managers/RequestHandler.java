package server.managers;

import common.network.Serializer;
import common.requests.Request;
import common.responses.Response;
import server.commands.Command;

import java.nio.ByteBuffer;

public class RequestHandler {
    private CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public <T extends Response> T handleRequest(ByteBuffer buffer) {
        Request request;
        T response;

        try {
            request = Serializer.deserializeObject(buffer);
            Command command = commandManager.getCommands().get(request.getCommandName());
            response = (T) command.execute(request);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
