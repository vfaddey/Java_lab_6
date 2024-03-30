package common.Responses;

import java.io.Serializable;

public class ResponseDTO implements Serializable {
    public Response response;

    public <T extends Response> ResponseDTO(T response) {
        this.response = response;
    }
}
