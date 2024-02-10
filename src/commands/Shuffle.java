package commands;

import commands.interfaces.CommandWithoutParameters;

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
