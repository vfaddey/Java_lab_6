package client;

import client.managers.*;

import java.io.IOException;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        Receiver receiver = new Receiver(SERVER_ADDRESS, SERVER_PORT);
        ConsoleHandler consoleHandler = new ConsoleHandler(receiver);
        receiver.setConsoleHandler(consoleHandler);
        try {
            receiver.connect();
            consoleHandler.listen();
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            receiver.close();
        }
    }
}
