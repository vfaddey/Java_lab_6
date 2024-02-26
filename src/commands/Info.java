package commands;

import interfaces.CommandWithoutParameters;

public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName) {
        super(consoleName, "<без параметров> Вывести информацию о коллекции", "");
    }

    @Override
    public void execute() {
        collectionManager.getSender().getConsoleHandler().println(collectionManager.getInformation());
    }
}
