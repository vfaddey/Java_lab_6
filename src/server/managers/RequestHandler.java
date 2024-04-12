package server.managers;

import common.network.Serializer;
import common.requests.RequestDTO;
import common.responses.Response;
import server.commands.Command;

import java.nio.ByteBuffer;

public class RequestHandler {
    private final CommandManager commandManager;

    public RequestHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public <T extends Response> T handleRequest(ByteBuffer buffer) {
        T response;
        RequestDTO requestDTO;
        try {
            requestDTO = Serializer.deserializeObject(buffer);
            Command command = commandManager.getCommands().get(requestDTO.getRequest().getCommandName());
            System.out.println("Получено: " + requestDTO.getRequest());
            response = (T) command.execute(requestDTO);
            return response;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
