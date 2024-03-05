package managers;

import exceptions.ConnectionFailedException;
import exceptions.NoConnectionException;

import java.io.*;
import java.net.Socket;

public class Receiver {
    private ConsoleHandler consoleHandler;
    private final int port;
    private final String serverAddress;
    private OutputStream output;
    private InputStream input;
    private PrintWriter writer;
    private BufferedReader reader;
    private Socket serverSocket;

    public Receiver(String address, int port) {
        this.port = port;
        this.serverAddress = address;
    }

    public void connect() {
        try {
            this.serverSocket = new Socket(serverAddress, port);
            this.output = serverSocket.getOutputStream();
            this.input = serverSocket.getInputStream();

            this.writer = new PrintWriter(output, true);
            this.reader = new BufferedReader(new InputStreamReader(input));

        } catch (IOException e) {
            throw new ConnectionFailedException("Подключение не удалось!");
        }
    }

    public void receive() throws IOException {
        if (this.reader != null) {
            String response = this.reader.readLine();
            processServerResponse(response);
        } else {
            throw new NoConnectionException("Нет подключения к серверу!");
        }
    }

    public void write(String request) throws IOException {
        this.writer.println(request);
        receive();
    }

    public void processServerResponse(String response) {
        consoleHandler.println(response);
    }

    public void execute(String command) {

    }

    public void setConsoleHandler(ConsoleHandler consoleHandler) {
        this.consoleHandler = consoleHandler;
    }
}
