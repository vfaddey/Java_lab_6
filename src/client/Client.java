package client;

import client.managers.*;
import client.network.TCPClient;
import common.requests.HelpRequest;
import common.requests.InfoRequest;
import common.requests.SaveRequest;
import common.requests.ShowRequest;
import common.responses.Response;

import java.io.IOException;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Receiver receiver = new Receiver(SERVER_ADDRESS, SERVER_PORT);
//        ConsoleHandler consoleHandler = new ConsoleHandler(receiver);
//        receiver.setConsoleHandler(consoleHandler);
//        consoleHandler.listen();

        RequestManager requestManager = new RequestManager(
                new ShowRequest("show"),
                new HelpRequest("help"),
                new InfoRequest("info"),
                new SaveRequest("save"));

        TCPClient tcpClient = new TCPClient(SERVER_ADDRESS, SERVER_PORT);
        Sender sender = new Sender(tcpClient);
        ConsoleHandler consoleHandler = new ConsoleHandler(receiver, requestManager, sender);
        tcpClient.run();
        consoleHandler.listen();
//        Response response = sender.sendRequest(new ShowRequest("show"));
//        System.out.println(response);
    }
}
