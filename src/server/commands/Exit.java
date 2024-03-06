package server.commands;

import server.interfaces.CommandWithoutParameters;

public class Exit extends Command implements CommandWithoutParameters {
    public Exit(String consoleName) {
        super(consoleName, "<Без параметров> Завершает работу без сохранения файла");
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
