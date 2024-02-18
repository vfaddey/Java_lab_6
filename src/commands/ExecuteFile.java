package commands;

import exceptions.*;
import interfaces.CommandWithParameters;
import managers.ConsoleHandler;

import java.io.IOException;

public class ExecuteFile extends Command implements CommandWithParameters {
    public ExecuteFile(String nameInConsole) {
        super(nameInConsole, "<Путь к файлу> Выполняет содержимое скрипта", "Код скрипта выполнен!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        try {
            ConsoleHandler.ScriptHandler.readCommands(parameters[0], collectionManager.getConsoleHandler().getCommandManager());
            printSuccess();
        } catch (IOException | WrongParameterException | IncorrectFilenameException | ElementNotFoundException | CommandNotExistsException | NullUserRequestException e) {
            throw new WrongParameterException("Файл не найден или нет доступа к файлу.");
        } catch (RecursionExecutionException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
        }
    }
}
