package interfaces;

import exceptions.*;


public interface CommandWithParameters {
    void execute(String... parameters) throws WrongParameterException;
}
