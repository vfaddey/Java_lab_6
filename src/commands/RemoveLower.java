package commands;

import commands.interfaces.CommandWithParameters;
import model.Organization;

public class RemoveLower extends Command implements CommandWithParameters {
    public RemoveLower(String nameInConsole) {
        super(nameInConsole, "Удаляет элементы меньше заданного", "Элементы удалены!");
    }

    @Override
    public void execute(String... parameters)  {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) > 0);
    }
}
