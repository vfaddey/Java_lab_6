package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Shuffle extends Command implements CommandWithoutParameters {
    public Shuffle(String nameInConsole) {
        super(nameInConsole, "Перемешивает элементы в коллекции", "Элементы коллекции перемешаны!");
    }

    @Override
    public void execute() {
        collectionManager.shuffleCollection();
        printSuccess();
    }
}
