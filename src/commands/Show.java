package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;
import model.Organization;

public class Show extends Command implements CommandWithoutParameters {

    public Show(String consoleName) {
        super(consoleName, "Выводит коллекцию", "");
    }

    @Override
    public void execute() {
        for (Organization organization : collectionManager.getCollection()) {
            System.out.println(organization);
        }
    }
}
