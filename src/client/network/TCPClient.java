package client.network;

import common.network.NetworkApp;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient implements NetworkApp {
    private String host;
    private int port;
    private Socket socket;

    private InputStream inputStream;
    private OutputStream outputStream;

    public TCPClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public void run() {

    }

    @Override
    public void openConnection() {

    }
}
