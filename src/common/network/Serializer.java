package common.network;

import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.ResponseDTO;

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

    public static RequestDTO deserializeObject(ByteBuffer buffer) {
        try (ByteArrayInputStream bais = new ByteArrayInputStream(buffer.array());
             ObjectInputStream ois = new ObjectInputStream(bais)) {
            return (RequestDTO) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
