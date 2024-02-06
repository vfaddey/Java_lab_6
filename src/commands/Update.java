package commands;

import interfaces.CommandWithParameters;
import managers.CollectionManager;

public class Update extends Command implements CommandWithParameters {
    public Update(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Обновляет элемент коллекции по id");
    }

    @Override
    public void execute(String... parameters) {

    }
}
