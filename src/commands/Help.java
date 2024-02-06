package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;
import managers.ConsoleHandler;

import java.util.HashSet;

public class Help extends Command implements CommandWithoutParameters {
    public Help(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Информация по всем командам");
    }

    @Override
    public void execute() {
        HashSet<Command> commands = collectionManager.getConsoleHandler().getCommandManager().getCommands();
        System.out.println("Все доступные команды:");
        for (Command command : commands) {
            System.out.println(command.getNameInConsole() + " | " + command.getDescription());
        }
    }
}
