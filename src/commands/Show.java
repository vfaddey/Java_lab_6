package commands;

import commands.interfaces.CommandWithoutParameters;
import model.Organization;

public class Show extends Command implements CommandWithoutParameters {

    public Show(String consoleName) {
        super(consoleName, "Выводит коллекцию", "");
    }

    @Override
    public void execute() {
        if (collectionManager.getCollection().size() != 0) {
            for (Organization organization : collectionManager.getCollection()) {
                collectionManager.getConsoleHandler().println(organization);
            }
        } else {
            collectionManager.getConsoleHandler().println("В коллекции пока нет элементов(");
        }

    }
}
