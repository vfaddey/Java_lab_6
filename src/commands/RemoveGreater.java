package commands;

import commands.interfaces.CommandWithParameters;
import exceptions.WrongParameterException;
import model.Organization;

public class RemoveGreater extends Command implements CommandWithParameters {
    public RemoveGreater(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Удаляет элементы больше заданного", "Элементы удалены!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) < 0);
    }
}
