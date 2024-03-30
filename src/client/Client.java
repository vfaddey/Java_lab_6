package client;

import client.managers.*;
import client.network.TCPClient;
import common.Requests.HelpRequest;
import common.Requests.ShowRequest;
import common.Responses.Response;
import server.managers.RequestHandler;

import java.io.IOException;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Receiver receiver = new Receiver(SERVER_ADDRESS, SERVER_PORT);
//        ConsoleHandler consoleHandler = new ConsoleHandler(receiver);
//        receiver.setConsoleHandler(consoleHandler);
//        consoleHandler.listen();

        TCPClient tcpClient = new TCPClient(SERVER_ADDRESS, SERVER_PORT);
        ConsoleHandler consoleHandler = new ConsoleHandler(receiver);
        ResponseHandler responseHandler = new ResponseHandler(consoleHandler);
        Sender sender = new Sender(tcpClient);
        tcpClient.run();
        Response response = sender.sendRequest(new ShowRequest("show"));
        System.out.println(response);
    }
}
