package commands;


import interfaces.CommandWithoutParameters;
import model.Organization;

public class RemoveGreater extends Command implements CommandWithoutParameters {
    public RemoveGreater(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Удаляет элементы больше заданного", "Элементы удалены!");
    }

    @Override
    public void execute() {
        Organization comparator = collectionManager.interactiveOrganizationCreation();
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) < 0);
    }
}
