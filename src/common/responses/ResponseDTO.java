package common.responses;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    private final Response response;

    public <T extends Response> ResponseDTO(T response) {
        this.response = response;
    }

    public Response getResponse() {
        return response;
    }
}
