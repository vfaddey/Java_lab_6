package server.interfaces;

import server.exceptions.*;

import java.io.IOException;


public interface CommandWithParameters {
    void execute(String... parameters) throws WrongParameterException, IOException;
}
