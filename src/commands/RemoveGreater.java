package commands;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import commands.interfaces.CommandWithParameters;

public class RemoveGreater extends Command implements CommandWithParameters {
    public RemoveGreater(String nameInConsole, String description, String successPhrase) {
        super(nameInConsole, description, successPhrase);
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException {

    }
}
