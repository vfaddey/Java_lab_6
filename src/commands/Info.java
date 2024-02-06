package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Вывести информацию о коллекции");
    }

    @Override
    public void execute() {

    }
}
