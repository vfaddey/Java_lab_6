package managers;

import exceptions.ConnectionFailedException;
import exceptions.NoConnectionException;

import java.io.*;
import java.net.Socket;

public class Receiver {
    private ConsoleHandler consoleHandler;
    private final int port;
    private final String serverAddress;
    private BufferedWriter writer;
    private BufferedReader reader;
    private Socket serverSocket;

    public Receiver(String address, int port) {
        this.port = port;
        this.serverAddress = address;
    }

    public void connect() throws IOException {
        try {
            this.serverSocket = new Socket(serverAddress, port);

            OutputStreamWriter writer = new OutputStreamWriter(serverSocket.getOutputStream());
            InputStreamReader reader = new InputStreamReader(serverSocket.getInputStream());

            this.reader = new BufferedReader(reader);
            this.writer = new BufferedWriter(writer);

        } catch (IOException e) {
            throw new ConnectionFailedException("Подключение не удалось!");
        }
    }

    public void close() {
        try {
            if (serverSocket != null) serverSocket.close();
            if (writer != null) writer.close();
            if (reader != null) reader.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }

    public void receive() throws IOException {
        String response;
        if (this.reader != null) {
            while ((response = this.reader.readLine()) != null) {
                processServerResponse(response);
                if (!this.reader.ready()) break;
            }
        } else {
            throw new NoConnectionException("Нет подключения к серверу!");
        }
    }

    public void write(String request) throws IOException, InterruptedException {
        this.writer.write(request);
        this.writer.newLine();
        this.writer.flush();
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
