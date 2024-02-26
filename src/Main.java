import commands.*;
import exceptions.*;
import interfaces.FileManager;
import managers.*;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException, IOException, NullUserRequestException {

        Scanner scanner = new Scanner(System.in);
        FileManager fileManager = new CSVHandler();
        CommandManager commandManager = new CommandManager(fileManager);
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager);
        Sender sender = new Sender(consoleHandler);
        CollectionManager collectionManager = new CollectionManager(consoleHandler, fileManager, sender);
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
                new Info("info"),
                new RemoveGreater("remove_greater"),
                new RemoveLower("remove_lower"));

        consoleHandler.listen();
    }
}
