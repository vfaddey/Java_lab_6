package interfaces;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;

public interface CommandWithParameters {
    void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException;
}
