package commands;

import interfaces.CommandWithoutParameters;
import managers.CollectionManager;

public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName) {
        super(consoleName, "Вывести информацию о коллекции", "");
    }

    @Override
    public void execute() {

    }
}
