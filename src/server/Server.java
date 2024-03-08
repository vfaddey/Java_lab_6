package server;

import server.commands.*;
import server.exceptions.*;
import server.interfaces.FileManager;
import server.managers.CSVHandler;
import server.managers.CollectionManager;
import server.managers.CommandManager;
import server.managers.Sender;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket;
        Socket clientSocket = null;
        InputStream input = null;
        BufferedReader reader = null;

        OutputStream output = null;
        BufferedWriter writer = null;


        try {
            serverSocket = new ServerSocket(PORT);
            FileManager fileManager = new CSVHandler();
            CommandManager commandManager = new CommandManager(fileManager);
            Sender sender = new Sender(PORT);
            CollectionManager collectionManager = new CollectionManager(fileManager, sender, "src/server/collection.csv");
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
            System.out.println("Сервер запущен! Порт: " + PORT);

            while (true) {
                try {
                    clientSocket = serverSocket.accept();
                    System.out.println("Подключился клиент: " + serverSocket);

                    input = clientSocket.getInputStream();
                    reader = new BufferedReader(new InputStreamReader(input));

                    output = clientSocket.getOutputStream();
                    writer = new BufferedWriter(new OutputStreamWriter(output));

                    sender.setWriter(writer);
                    sender.setReader(reader);

                    while (true) {
                        String request = reader.readLine();
                        System.out.println(request);
                        if (request.equalsIgnoreCase("exit")) {
                            break;
                        }
                        commandManager.exec(request);
                    }
                } catch (IOException e) {
                    System.out.println(e.toString());
                } finally {
                    if (clientSocket != null) clientSocket.close();
                    if (reader != null) reader.close();
                    if (writer != null) writer.close();
                    if (input != null) input.close();
                    if (output != null) output.close();
                }

            }
        } catch (IncorrectFilenameException | ElementNotFoundException | NullUserRequestException |
                 CommandNotExistsException | WrongParameterException e) {
            System.out.println("closed");
        }
    }
}
