package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Добавить новую организацию в коллекцию");
    }

    @Override
    public void execute() {
        collectionManager.interactiveOrganizationCreation();
    }
}