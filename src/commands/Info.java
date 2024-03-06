package commands;

import interfaces.CommandWithoutParameters;

import java.io.IOException;

public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName) {
        super(consoleName, "<без параметров> Вывести информацию о коллекции", "");
    }

    @Override
    public void execute() throws IOException {
        collectionManager.getSender().send(collectionManager.getInformation());
    }
}
