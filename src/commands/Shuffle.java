package commands;

import interfaces.CommandWithoutParameters;

import java.io.IOException;

public class Shuffle extends Command implements CommandWithoutParameters {
    public Shuffle(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Перемешивает элементы в коллекции", "Элементы коллекции перемешаны!");
    }

    @Override
    public void execute() throws IOException {
        collectionManager.shuffleCollection();
        printSuccess();
    }
}
