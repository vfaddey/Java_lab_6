package client;

import client.managers.*;
import client.network.TCPClient;
import common.requests.*;

import java.io.IOException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {

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
                new ExitRequest("exit"),
                new FilterContainsNameRequest("filter_contains_name"));

        TCPClient tcpClient = new TCPClient(SERVER_ADDRESS, SERVER_PORT);
        Sender sender = new Sender(tcpClient);
        ResponseHandler responseHandler = new ResponseHandler();
        ConsoleHandler consoleHandler = new ConsoleHandler(requestManager, sender, responseHandler);
        try {
            tcpClient.run();
            consoleHandler.listen();
        } catch (ConnectException e) {
            consoleHandler.printError("Сервер не запущен!");
        } catch (SocketTimeoutException e) {
            consoleHandler.printError(e.toString());
        }
    }
}
