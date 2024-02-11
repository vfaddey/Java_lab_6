package commands;

import commands.interfaces.CommandWithoutParameters;

public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName) {
        super(consoleName, "Вывести информацию о коллекции", "");
    }

    @Override
    public void execute() {
        collectionManager.getConsoleHandler().println(collectionManager.getInformation());
    }
}
