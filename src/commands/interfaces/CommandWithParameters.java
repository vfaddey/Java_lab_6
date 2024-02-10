package commands.interfaces;

import exceptions.*;

import java.io.IOException;

public interface CommandWithParameters {
    void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException, IOException, CommandNotExistsException, NullUserRequestException;
}
