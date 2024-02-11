package commands;

import commands.interfaces.CommandWithoutParameters;
import exceptions.WrongParameterException;

public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName) {
        super(consoleName, "Добавить новую организацию в коллекцию", "Новый элемент добавлен!");
    }

    @Override
    public void execute() throws WrongParameterException {
        collectionManager.interactiveOrganizationCreation();
        printSuccess();
    }

    public void executeFromScript(String... parameters) {
        collectionManager.organizationCreationFromFile(parameters);
        printSuccess();
    }

}
