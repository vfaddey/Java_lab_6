package server;

import commands.*;
import interfaces.FileManager;
import managers.*;

import javax.net.ssl.SSLServerSocket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        String response;

        FileManager fileManager = new CSVHandler();
        CommandManager commandManager = new CommandManager(fileManager);
//        Sender sender = new Sender(new ConsoleHandler(commandManager));
//        CollectionManager collectionManager = new CollectionManager(fileManager, sender, "src/collection.csv");
//        commandManager.setCollectionManager(collectionManager);
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


        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен! Порт: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + serverSocket);

                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String request = reader.readLine();
                if (request.equals("hello, server")) {
                    response = "hello, client";
                } else {
                    response = "(";
                }
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
