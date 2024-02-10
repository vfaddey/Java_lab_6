package commands;

import commands.interfaces.CommandWithoutParameters;

public class Exit extends Command implements CommandWithoutParameters {
    public Exit(String consoleName) {
        super(consoleName, "Завершает работу без сохранения файла");
    }

    @Override
    public void execute() {
        System.exit(0);
    }
}
