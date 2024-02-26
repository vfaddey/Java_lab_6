package commands;

import interfaces.CommandWithoutParameters;

import java.util.HashSet;

public class Help extends Command implements CommandWithoutParameters {
    public Help(String consoleName) {
        super(consoleName, "<Без параметров> Информация по всем командам", "");
    }

    @Override
    public void execute() {
        int padding = 40;
        HashSet<Command> commands = collectionManager.getSender().getConsoleHandler().getCommandManager().getCommands();
        StringBuilder output = new StringBuilder("Все доступные команды:\n");
        for (Command command : commands) {
            output.append(String.format("%-" + padding + "s | %s\n", command.getNameInConsole(), command.getDescription()));
        }
        collectionManager.getSender().getConsoleHandler().println(output);
    }
}
