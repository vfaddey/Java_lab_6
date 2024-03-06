package commands;

import interfaces.CommandWithoutParameters;

import java.io.IOException;

public class Clear extends Command implements CommandWithoutParameters {
    public Clear(String consoleName) {
        super(consoleName, "<Без параметров> Очистить коллекцию", "Коллекция очищена!");
    }

    @Override
    public void execute() throws IOException {
        collectionManager.clearCollection();
        printSuccess();
    }
}
