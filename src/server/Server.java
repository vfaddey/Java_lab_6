package server;

import server.commands.*;
import server.interfaces.FileManager;
import server.managers.*;
import server.network.TCPServer;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        FileManager csvHandler = new CSVHandler();
        CollectionManager collectionManager = new CollectionManager(csvHandler, "src/server/collection.csv");
        CommandManager commandManager = new CommandManager(csvHandler);
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
                new Info("info"),
                new RemoveGreater("remove_greater"),
                new RemoveLower("remove_lower"));
        RequestHandler requestHandler = new RequestHandler(commandManager);
        TCPServer server = new TCPServer(commandManager, requestHandler, new Logger("logs.log"));
        try {
            server.openConnection();
            server.run();
        } finally {
            server.close();
        }
    }
}
