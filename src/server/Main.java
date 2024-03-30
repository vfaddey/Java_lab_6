package server;

import server.commands.*;
import server.interfaces.FileManager;
import server.managers.CSVHandler;
import server.managers.CommandManager;
import server.managers.RequestHandler;
import server.network.TCPServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandManager commandManager = new CommandManager(new CSVHandler());
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
        RequestHandler requestHandler = new RequestHandler(commandManager);
        TCPServer server = new TCPServer(commandManager, requestHandler);
        server.openConnection();
        server.run();
    }
}
