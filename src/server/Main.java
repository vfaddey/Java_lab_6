package server;

import server.interfaces.FileManager;
import server.managers.CSVHandler;
import server.managers.CommandManager;
import server.managers.RequestHandler;
import server.network.TCPServer;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        CommandManager commandManager = new CommandManager(new CSVHandler());
        RequestHandler requestHandler = new RequestHandler(commandManager);
        TCPServer server = new TCPServer(commandManager, requestHandler);
        server.openConnection();
        server.run();
    }
}
