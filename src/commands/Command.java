package commands;

import managers.CollectionManager;

public abstract class Command {
    private String nameInConsole;
    private String description;
    protected CollectionManager collectionManager;

    public Command(String nameInConsole, String description) {
        this.nameInConsole = nameInConsole;
        this.description = description;
    }

    public Command(String nameInConsole, CollectionManager collectionManager, String description) {
        this.nameInConsole = nameInConsole;
        this.collectionManager = collectionManager;
        this.description = description;
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
