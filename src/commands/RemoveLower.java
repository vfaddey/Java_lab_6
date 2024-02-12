package commands;

import commands.interfaces.CommandWithParameters;

public class RemoveLower extends Command implements CommandWithParameters {
    public RemoveLower(String nameInConsole, String description, String successPhrase) {
        super(nameInConsole, description, successPhrase);
    }

    @Override
    public void execute(String... parameters)  {

    }
}
