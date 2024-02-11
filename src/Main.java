import commands.*;
import exceptions.CommandNotExistsException;
import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import managers.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException {
        Scanner scanner = new Scanner(System.in);
        CommandManager commandManager = new CommandManager();
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager);
        CollectionManager collectionManager = new CollectionManager(consoleHandler);
        commandManager.setCollectionManager(collectionManager);
        commandManager.addCommands(
                new Add("add"),
                new Clear("clear"),
                new Save("save"),
                new Show("show"),
                new Help("help"),
                new Exit("exit"),
                new RemoveById("remove_by_id"),
                new Update("update"),
                new Shuffle("shuffle"),
                new FilterContainsName("filter_contains_name"),
                new FilterLessThanAnnualTurnover("filter_less_than_annual_turnover"),
                new RemoveAnyByAnnualTurnover("remove_any_by_annual_turnover"),
                new ExecuteFile("execute_script"),
                new Info("info"));

        consoleHandler.listen();
    }
}
