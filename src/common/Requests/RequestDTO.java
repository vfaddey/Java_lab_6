package common.Requests;

import java.io.Serializable;

public class RequestDTO implements Serializable {
    public Request request;

    public <T extends Request> RequestDTO(T request) {
        this.request = request;
    }
}
