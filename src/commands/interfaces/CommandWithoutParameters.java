package commands.interfaces;

import exceptions.WrongParameterException;

public interface CommandWithoutParameters {
    void execute() throws WrongParameterException;
}
