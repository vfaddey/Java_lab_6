package interfaces;

import exceptions.WrongParameterException;

public interface CommandWithParameters {
    void execute(String... parameters) throws WrongParameterException;
}
