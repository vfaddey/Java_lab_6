package client.managers;

import client.network.TCPClient;
import common.Requests.Request;
import common.Requests.RequestDTO;
import common.Responses.Response;
import common.Responses.ResponseDTO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Sender {
    private final TCPClient client;
    private final ResponseHandler responseHandler;

    public Sender(TCPClient client, ResponseHandler responseHandler) {
        this.client = client;
        this.responseHandler = responseHandler;
    }


    public <T extends Request> Response sendRequest(T request) throws IOException, ClassNotFoundException {
        sendObject(request);
        try {
            ResponseDTO responseDTO = (ResponseDTO) recieveObject();
            return responseDTO.getResponse();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public <T extends Request> void sendObject(T request) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        RequestDTO requestDTO = new RequestDTO(request);
        oos.writeObject(requestDTO);
        oos.close();

        sendData(baos.toByteArray());
    }

    private void sendData(byte[] bytes) {
        try {
            client.getOutputStream().write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Object recieveObject() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream = new ObjectInputStream(this.client.getInputStream());
        return objectInputStream.readObject();
    }
}
