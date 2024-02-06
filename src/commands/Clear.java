package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Clear extends Command implements CommandWithoutParameters {
    public Clear(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Очистить коллекцию");
    }

    @Override
    public void execute() {
        collectionManager.getCollection().clear();
    }
}