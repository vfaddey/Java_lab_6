package commands;

import commands.interfaces.CommandWithoutParameters;


public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName) {
        super(consoleName, "Добавить новую организацию в коллекцию", "Новый элемент добавлен!");
    }

    @Override
    public void execute() {
        collectionManager.addNewElement();
        printSuccess();
    }

    public void executeFromScript(String... parameters) {
        collectionManager.organizationCreationFromFile(parameters);
        printSuccess();
    }

}
