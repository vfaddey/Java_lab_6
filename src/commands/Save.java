package commands;

import interfaces.CommandWithParameters;
import interfaces.CommandWithoutParameters;
import managers.CollectionManager;
import managers.FileManager;

public class Save extends Command implements CommandWithoutParameters, CommandWithParameters {
    public Save(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Сохранить коллекцию (в тот же файл)");
    }

    @Override
    public void execute() {
        FileManager.writeCollectionToCSV(collectionManager.getCollection(), collectionManager.getCollectionFilename());
    }

    @Override
    public void execute(String... parameters) {

    }
}
