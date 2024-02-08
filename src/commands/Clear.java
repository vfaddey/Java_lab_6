package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Clear extends Command implements CommandWithoutParameters {
    public Clear(String consoleName) {
        super(consoleName, "Очистить коллекцию", "Коллекция очищена!");
    }

    @Override
    public void execute() {
        collectionManager.clearCollection();
        printSuccess();
    }
}
