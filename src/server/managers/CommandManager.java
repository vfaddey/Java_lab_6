package server.managers;

import client.managers.Validator;
import server.commands.Command;
import common.exceptions.*;

import server.interfaces.FileManager;


import java.util.HashMap;


/**
 * Handles console requests, executes server.commands
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

//    public void exec(String userInput) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException, CommandNotExistsException, NullUserRequestException, IOException {
//        try {
//            String[] splitted = splitUserRequest(userInput);
//            String commandName = splitted[0];
//            if (!commands.containsKey(commandName)) throw new CommandNotExistsException("Такой команды нет");
//
//            Command command = this.commands.get(commandName);
//            String[] parameters = new String[splitted.length-1];
//            for (int i = 1; i < splitted.length; i++) {
//                parameters[i-1] = splitted[i];
//            }
//            if (command instanceof CommandWithParameters && parameters.length != 0) {
//                ((CommandWithParameters) command).execute(parameters);
//            } else if (command instanceof CommandWithoutParameters) {
//                ((CommandWithoutParameters) command).execute();
//            } else if (parameters.length == 0 && command instanceof CommandWithParameters) {
//                throw new WrongParameterException("Вы не ввели параметр.");
//            }
//        } catch (CommandNotExistsException | WrongParameterException | NullUserRequestException e) {
//            collectionManager.getSender().send(e.toString(), MessageType.ERROR);
//        }
//    }


    public void addCommands(Command... commands) {
        for (Command command : commands) {
            this.commands.put(command.getNameInConsole(), command);
            command.setCollectionManager(collectionManager);
            command.setFileManager(fileManager);
            command.setCommandManager(this);
        }
    }

    public HashMap<String, Command> getCommands() {
        return commands;
    }
}
