package commands;

import commands.interfaces.CommandWithoutParameters;

public class Clear extends Command implements CommandWithoutParameters {
    public Clear(String consoleName) {
        super(consoleName, "Очистить коллекцию", "Коллекция очищена!");
    }

    @Override
    public void execute() {
        collectionManager.clearCollection();
        printSuccess();
    }
}
