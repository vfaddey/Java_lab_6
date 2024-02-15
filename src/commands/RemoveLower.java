package commands;

import commands.interfaces.CommandWithoutParameters;
import exceptions.WrongParameterException;
import model.Organization;

public class RemoveLower extends Command implements CommandWithoutParameters {
    public RemoveLower(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Удаляет элементы меньше заданного", "Элементы удалены!");
    }

    @Override
    public void execute() throws WrongParameterException {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) > 0);
    }
}
