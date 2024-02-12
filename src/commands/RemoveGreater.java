package commands;

import commands.interfaces.CommandWithParameters;

public class RemoveGreater extends Command implements CommandWithParameters {
    public RemoveGreater(String nameInConsole, String description, String successPhrase) {
        super(nameInConsole, description, successPhrase);
    }

    @Override
    public void execute(String... parameters)  {

    }
}
