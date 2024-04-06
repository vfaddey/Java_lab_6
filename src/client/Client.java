package client;

import client.managers.*;
import client.network.TCPClient;
import common.requests.*;

import java.io.IOException;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        Receiver receiver = new Receiver(SERVER_ADDRESS, SERVER_PORT);
//        ConsoleHandler consoleHandler = new ConsoleHandler(receiver);
//        receiver.setConsoleHandler(consoleHandler);
//        consoleHandler.listen();

        RequestManager requestManager = new RequestManager(
                new ShowRequest("show"),
                new HelpRequest("help"),
                new InfoRequest("info"),
                new SaveRequest("save"),
                new RemoveByIdRequest("remove_by_id"),
                new FilterLessThanAnnualTurnoverRequest("filter_less_than_annual_turnover"),
                new ClearRequest("clear"),
                new ShuffleRequest("shuffle"),
                new AddRequest("add"),
                new UpdateRequest("update"),
                new AddRequest("remove_greater"),
                new AddRequest("remove_lower"),
                new RemoveAnyByAnnualTurnoverRequest("remove_by_annual_turnover"),
                new ExecuteScriptRequest("execute_script"),
                new ExitRequest("exit"));

        TCPClient tcpClient = new TCPClient(SERVER_ADDRESS, SERVER_PORT);
        Sender sender = new Sender(tcpClient);
        ResponseHandler responseHandler = new ResponseHandler();
        ConsoleHandler consoleHandler = new ConsoleHandler(receiver, requestManager, sender, responseHandler);
        tcpClient.run();
        consoleHandler.listen();
    }
}
