package server.commands;


import server.interfaces.CommandWithoutParameters;
import common.model.Organization;

import java.io.IOException;

public class RemoveGreater extends Command implements CommandWithoutParameters {
    public RemoveGreater(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Удаляет элементы больше заданного", "Элементы удалены!");
    }

    @Override
    public void execute() throws IOException {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) < 0);
    }
}
