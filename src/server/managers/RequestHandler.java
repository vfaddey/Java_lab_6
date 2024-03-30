package server.managers;

import common.Responses.EmptyResponse;
import common.network.Serializer;
import common.Requests.Request;
import common.Responses.Response;
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
//            response = command.execute(request);
            response = (T) new EmptyResponse();
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
