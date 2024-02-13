package commands;

import commands.interfaces.CommandWithParameters;
import model.Organization;

public class RemoveGreater extends Command implements CommandWithParameters {
    public RemoveGreater(String nameInConsole) {
        super(nameInConsole, "Удаляет элементы больше заданного", "Элементы удалены!");
    }

    @Override
    public void execute(String... parameters)  {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) < 0);
    }
}
