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
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException, IOException, CommandNotExistsException, NullUserRequestException {
        try {
            ConsoleHandler.ScriptHandler.readCommands(parameters[0], collectionManager.getConsoleHandler().getCommandManager());
            printSuccess();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (WrongParameterException e) {
            throw new RuntimeException(e);
        } catch (IncorrectFilenameException e) {
            throw new RuntimeException(e);
        } catch (ElementNotFoundException e) {
            throw new RuntimeException(e);
        } catch (CommandNotExistsException e) {
            throw new RuntimeException(e);
        } catch (NullUserRequestException e) {
            throw new RuntimeException(e);
        }
    }
}
