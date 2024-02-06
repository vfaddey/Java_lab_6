package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;
import model.Organization;

public class Show extends Command implements CommandWithoutParameters {

    public Show(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Выводит коллекцию");
    }

    @Override
    public void execute() {
        for (Organization organization : collectionManager.getCollection()) {
            System.out.println(organization);
        }
    }
}
