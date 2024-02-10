package commands;

import commands.interfaces.CommandWithoutParameters;

import java.util.HashSet;

public class Help extends Command implements CommandWithoutParameters {
    public Help(String consoleName) {
        super(consoleName, "Информация по всем командам", "");
    }

    @Override
    public void execute() {
        HashSet<Command> commands = collectionManager.getConsoleHandler().getCommandManager().getCommands();
        collectionManager.getConsoleHandler().print("Все доступные команды:");
        for (Command command : commands) {
            collectionManager.getConsoleHandler().print(command.getNameInConsole() + " | " + command.getDescription());
        }
    }
}
