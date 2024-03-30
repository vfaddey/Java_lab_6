package server.interfaces;

import common.exceptions.*;

import java.io.IOException;


public interface CommandWithParameters {
    void execute(String... parameters) throws WrongParameterException, IOException;
}
