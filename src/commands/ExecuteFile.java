package commands;

import exceptions.*;
import commands.interfaces.CommandWithParameters;
import managers.ConsoleHandler;

import java.io.IOException;

public class ExecuteFile extends Command implements CommandWithParameters {
    public ExecuteFile(String nameInConsole) {
        super(nameInConsole, "Выполняет содержимое скрипта", "Код скрипта выполнен!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        try {
            ConsoleHandler.ScriptHandler.readCommands(parameters[0], collectionManager.getConsoleHandler().getCommandManager());
            printSuccess();
        } catch (IOException | WrongParameterException | IncorrectFilenameException | ElementNotFoundException | CommandNotExistsException | NullUserRequestException e) {
            throw new WrongParameterException("Файл не найден или нет доступа к файлу.");
        }
    }
}
