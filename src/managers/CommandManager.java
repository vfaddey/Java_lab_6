package managers;

import commands.Command;
import exceptions.*;
import interfaces.CommandWithParameters;
import interfaces.CommandWithoutParameters;
import interfaces.FileManager;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;


/**
 * Handles console requests, executes commands
 */
public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();
    private CollectionManager collectionManager;
    private final FileManager fileManager;

    public CommandManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
        this.collectionManager.setFileManager(fileManager);
        for (String key : commands.keySet()) {
            commands.get(key).setCollectionManager(collectionManager);
            commands.get(key).setFileManager(fileManager);
        }
    }

    public void exec(String userInput) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException, CommandNotExistsException, NullUserRequestException, IOException {
        try {
            String[] splitted = splitUserRequest(userInput);
            String commandName = splitted[0];
            if (!commands.containsKey(commandName)) throw new CommandNotExistsException("Такой команды нет");

            Command command = this.commands.get(commandName);
            String[] parameters = new String[splitted.length-1];
            for (int i = 1; i < splitted.length; i++) {
                parameters[i-1] = splitted[i];
            }
            if (command instanceof CommandWithParameters && parameters.length != 0) {
                ((CommandWithParameters) command).execute(parameters);
            } else if (command instanceof CommandWithoutParameters) {
                ((CommandWithoutParameters) command).execute();
            } else if (parameters.length == 0 && command instanceof CommandWithParameters) {
                throw new WrongParameterException("Вы не ввели параметр.");
            }
        } catch (CommandNotExistsException | WrongParameterException | NullUserRequestException e) {
            collectionManager.getSender().getConsoleHandler().printError(e.toString());
            collectionManager.getSender().getConsoleHandler().listen();
        }

    }

    private String[] splitUserRequest(String request) throws NullUserRequestException {
        if (request.isEmpty()) throw new NullUserRequestException("Введена пустая строка");
        if (!request.contains(" ")) return new String[]{request};
        String command = request.split(" ", 2)[0];
        String[] parameters = request.split(" ", 2)[1].split(" ");
        if (parameters.length != 0) {
            for (int i = 0; i < parameters.length; i++) {
                if (parameters[i].isEmpty()) {
                    parameters[i] = null;
                }
            }
        }

        String[] processed;
        if (Validator.isArrayConsistsOfOnlyNull(parameters)) {
            processed = new String[]{command};
            return processed;
        } else {
            processed = new String[parameters.length + 1];
            processed[0] = command;
            System.arraycopy(parameters, 0, processed, 1, parameters.length);
        }
        return processed;
    }

    public void processFileCommands(String[] lines) throws NullUserRequestException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException, CommandNotExistsException, IOException {
        int i = 0;
        while (i < lines.length) {
            exec(lines[i]);
            i++;
        }
    }

    public void addCommands(Command... commands) {
        for (Command command : commands) {
            this.commands.put(command.getNameInConsole(), command);
            command.setCollectionManager(collectionManager);
            command.setFileManager(fileManager);
            command.setCommandManager(this);
        }
    }

    public HashSet<Command> getCommands() {
        return new HashSet<>(commands.values());
    }
}
