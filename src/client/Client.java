package client;

import interfaces.FileManager;
import managers.CSVHandler;
import managers.CollectionManager;
import managers.CommandManager;
import managers.ConsoleHandler;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {

        try (Socket serverSocket = new Socket(SERVER_ADDRESS, SERVER_PORT)) {
            OutputStream output = serverSocket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            writer.println("hello, server");

            InputStream input = serverSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
            String response = reader.readLine();
            System.out.println("Ответ сервера: " + response);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
