package server.commands;

import exceptions.*;
import server.interfaces.CommandWithParameters;
import client.managers.ConsoleHandler;

import java.io.IOException;
import java.util.Stack;

public class ExecuteFile extends Command implements CommandWithParameters {
    private final Stack<String> filenamesStack = new Stack<>();
    public ExecuteFile(String nameInConsole) {
        super(nameInConsole, "<Путь к файлу> Выполняет содержимое скрипта", "Код скрипта выполнен!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        try {
            if (!filenamesStack.contains(parameters[0])) {
                filenamesStack.push(parameters[0]);
                ConsoleHandler.ScriptHandler.readCommands(parameters[0], commandManager);
                printSuccess();
            } else {
                throw new RecursionExecutionException("Файл " + parameters[0] + " уже был вызван.");
            }

        } catch (IOException | WrongParameterException | IncorrectFilenameException | ElementNotFoundException | CommandNotExistsException | NullUserRequestException e) {
            throw new WrongParameterException("Файл не найден или нет доступа к файлу.");
        } catch (RecursionExecutionException e) {
            collectionManager.getSender().send(e.toString());
        }
    }
}
