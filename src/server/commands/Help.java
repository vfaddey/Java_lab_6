package server.commands;

import common.requests.Request;
import common.responses.HelpResponse;
import common.responses.Response;
import server.interfaces.CommandWithoutParameters;
import server.managers.MessageType;

import java.io.IOException;
import java.util.HashSet;

public class Help extends Command implements CommandWithoutParameters {
    public Help(String consoleName) {
        super(consoleName, "<Без параметров> Информация по всем командам", "");
    }

    @Override
    public void execute() throws IOException {
        int padding = 35;
        HashSet<Command> commands = (HashSet<Command>) commandManager.getCommands().values();
        StringBuilder output = new StringBuilder("Все доступные команды:\n");
        for (Command command : commands) {
            output.append(String.format("%-" + padding + "s | %s\n", command.getNameInConsole(), command.getDescription()));
        }
        collectionManager.getSender().send(output, MessageType.DEFAULT);
    }

    @Override
    public Response execute(Request request) {
        int padding = 35;
        HashSet<Command> commands = new HashSet<>(commandManager.getCommands().values());
        StringBuilder output = new StringBuilder("Все доступные команды:\n");
        for (Command command : commands) {
            output.append(String.format("%-" + padding + "s | %s\n", command.getNameInConsole(), command.getDescription()));
        }
        return new HelpResponse(this.getNameInConsole(), output.toString());
    }
}