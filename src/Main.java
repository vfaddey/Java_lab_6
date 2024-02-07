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
            commandManager.addCommands(
                    new Add("add", collectionManager),
                    new Clear("clear", collectionManager),
                    new Save("save", collectionManager),
                    new Show("show", collectionManager),
                    new Help("help", collectionManager),
                    new Exit("exit"),
                    new RemoveById("remove_by_id", collectionManager),
                    new Update("update", collectionManager));
            consoleHandler.listen();


            FileManager.writeCollectionToCSV(collection, filename);
            scanner.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
