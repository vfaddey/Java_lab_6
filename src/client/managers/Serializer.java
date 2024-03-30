package client.managers;

import common.Responses.Response;
import common.Responses.ResponseDTO;

import java.io.*;
import java.nio.ByteBuffer;

public class Serializer {
    public static <T extends Response> ByteBuffer serializeObject(T response) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(new ResponseDTO(response));
        oos.close();

        ByteBuffer buffer = ByteBuffer.allocate(baos.size());
        buffer.put(baos.toByteArray());
        return buffer;
    }

    public static Response deserializeObject(ByteBuffer buffer) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            ResponseDTO responseDTO = (ResponseDTO) ois.readObject();
            return responseDTO.getResponse();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
