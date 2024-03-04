package server;

import javax.net.ssl.SSLServerSocket;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final int PORT = 8888;

    public static void main(String[] args) {
        String response;
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен! Порт: " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Подключился клиент: " + serverSocket);

                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));

                String request = reader.readLine();
                if (request.equals("hello, server")) {
                    response = "hello, client";
                } else {
                    response = "(";
                }
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(response);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
