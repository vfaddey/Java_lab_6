package managers;

import commands.Command;
import exceptions.CommandNotExistsException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CommandManager {
    private HashSet<Command> commands = new HashSet<>();

    public CommandManager(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    public void exec(String commandName) throws CommandNotExistsException {
        if (!getConsoleCommandsNames().contains(commandName)) throw new CommandNotExistsException("Такой команды нет");
        for (Command command : commands) {
            if (command.getNameInConsole().equals(commandName)) {
                command.execute();
            }
        }
    }
    public void exec(String commandName, String[] parameters) {

    }

    public HashSet<String> getConsoleCommandsNames() {
        HashSet<String> names = new HashSet<>();
        for (Command command : commands) {
            names.add(command.getNameInConsole());
        }
        return names;
    }

    public void addCommands(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    public HashSet<Command> getCommands() {
        return commands;
    }
}
