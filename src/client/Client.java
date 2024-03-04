package client;

import interfaces.FileManager;
import managers.*;

import java.io.*;
import java.net.Socket;

public class Client {
    private static final String SERVER_ADDRESS = "127.0.0.1";
    private static final int SERVER_PORT = 8888;

    public static void main(String[] args) {
        Reciever reciever = new Reciever(SERVER_ADDRESS, SERVER_PORT);
        reciever.connect();


    }
}
