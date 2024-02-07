package commands;

import managers.CollectionManager;

public abstract class Command {
    private String nameInConsole;
    private String description;
    protected String successPhrase;
    protected CollectionManager collectionManager;

    public Command(String nameInConsole, String description) {
        this.nameInConsole = nameInConsole;
        this.description = description;
    }

    public Command(String nameInConsole, CollectionManager collectionManager, String description, String successPhrase) {
        this.nameInConsole = nameInConsole;
        this.collectionManager = collectionManager;
        this.description = description;
    }

    protected void printSuccess() {
        collectionManager.getConsoleHandler().print(successPhrase);
    }

    public String getNameInConsole() {
        return nameInConsole;
    }

    public String getDescription() {
        return description;
    }

    public void execute() {

    }

}
