package commands;

import interfaces.CommandWithoutParameters;
import model.Organization;

/**
 * Adds a new element to collection
 */
public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName) {
        super(consoleName, "<Без параметров> Добавить новую организацию в коллекцию", "Новый элемент добавлен!");
    }

    @Override
    public void execute() {
        Organization newElement = collectionManager.interactiveOrganizationCreation();
        collectionManager.addNewElement(newElement);
        printSuccess();
    }

    public void executeFromScript(String... parameters) {
        collectionManager.organizationCreationFromFile(parameters);
        printSuccess();
    }

}
