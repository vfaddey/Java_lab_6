package commands;

import interfaces.CommandWithoutParameters;
import model.Organization;

import java.io.IOException;

public class RemoveLower extends Command implements CommandWithoutParameters {
    public RemoveLower(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Удаляет элементы меньше заданного", "Элементы удалены!");
    }

    @Override
    public void execute() throws IOException {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) > 0);
    }
}
