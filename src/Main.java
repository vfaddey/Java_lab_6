import commands.*;
import exceptions.CommandNotExistsException;
import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import managers.*;
import model.*;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CommandNotExistsException, IncorrectFilenameException, ElementNotFoundException, WrongParameterException {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Путь к файлу с коллекцией: ");
        String filename = scanner.nextLine();
        CommandManager commandManager = new CommandManager();
        ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager);
        LinkedList<Organization> collection = FileManager.readCollectionFromCSV(filename, consoleHandler);
        CollectionManager collectionManager = new CollectionManager(collection, filename, consoleHandler);
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
                new RemoveAnyByAnnualTurnover("remove_any_by_annual_turnover"));

        consoleHandler.listen();
    }
}
