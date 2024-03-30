package server.commands;


import common.Requests.Request;
import common.Responses.EmptyResponse;
import common.Responses.Response;
import server.interfaces.FileManager;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.MessageType;

import java.io.IOException;

/**
 * Abstract class of command
 */
public abstract class Command {
    private final String nameInConsole;
    private final String description;
    protected String successPhrase;
    protected CollectionManager collectionManager;
    protected FileManager fileManager;
    protected CommandManager commandManager;

    public Command(String nameInConsole, String description) {
        this.nameInConsole = nameInConsole;
        this.description = description;
    }

    public Command(String nameInConsole, String description, String successPhrase) {
        this.nameInConsole = nameInConsole;
        this.description = description;
        this.successPhrase = successPhrase;
    }

    public void setCollectionManager(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    protected void printSuccess() throws IOException {
        collectionManager.getSender().send(successPhrase, MessageType.SUCCESS);
    }

    public String getNameInConsole() {
        return nameInConsole;
    }

    public String getDescription() {
        return description;
    }

    public void execute() throws IOException {

    }

    public Response execute(Request request) throws IOException {
        return new EmptyResponse();
    }

}
