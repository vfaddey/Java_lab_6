package commands;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import commands.interfaces.CommandWithParameters;

public class RemoveLower extends Command implements CommandWithParameters {
    public RemoveLower(String nameInConsole, String description, String successPhrase) {
        super(nameInConsole, description, successPhrase);
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException {

    }
}
