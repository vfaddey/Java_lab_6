package commands;

import interfaces.CommandWithoutParameters;

import java.io.IOException;
import java.util.HashSet;

public class Help extends Command implements CommandWithoutParameters {
    public Help(String consoleName) {
        super(consoleName, "<Без параметров> Информация по всем командам", "");
    }

    @Override
    public void execute() throws IOException {
        int padding = 35;
        HashSet<Command> commands = commandManager.getCommands();
        StringBuilder output = new StringBuilder("Все доступные команды:\n");
        for (Command command : commands) {
            output.append(String.format("%-" + padding + "s | %s\n", command.getNameInConsole(), command.getDescription()));
        }
        collectionManager.getSender().send(output);
    }
}