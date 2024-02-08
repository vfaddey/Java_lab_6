import commands.*;
import managers.*;
import model.*;

import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Путь к файлу с коллекцией: ");
            String filename = scanner.nextLine();
            CommandManager commandManager = new CommandManager();
            ConsoleHandler consoleHandler = new ConsoleHandler(scanner, commandManager, new Validator());
            LinkedList<Organization> collection = FileManager.readCollectionFromCSV(filename);
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
