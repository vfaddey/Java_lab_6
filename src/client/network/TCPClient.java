package client.network;

import common.exceptions.ClosureFailedException;
import common.exceptions.ConnectionFailedException;
import common.network.NetworkApp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TCPClient implements NetworkApp {
    private final String host;
    private final int port;
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() throws IOException {
        connect();
    }

    @Override
    public void openConnection() {
        try {
            connect();
        } catch (IOException e) {
            throw new ConnectionFailedException("Подключение не удалось.");
        }
    }

    private void connect() throws IOException {
        this.socket = new Socket();
        socket.connect(new InetSocketAddress(this.host, this.port));

        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
    }

    public void close() throws ClosureFailedException {
        try {
            if (this.socket != null) {
                this.socket.close();
            }
        } catch (IOException e) {
            throw new ClosureFailedException("Не удалось закрыть соединение");
        }
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public OutputStream getOutputStream() {
        return outputStream;
    }
}
