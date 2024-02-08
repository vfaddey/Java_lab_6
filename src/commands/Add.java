package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName) {
        super(consoleName, "Добавить новую организацию в коллекцию", "Новый элемент добавлен!");
    }

    @Override
    public void execute() {
        collectionManager.interactiveOrganizationCreation();
        printSuccess();
    }
}
