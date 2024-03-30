package common.requests;

import java.io.Serializable;

public class RequestDTO implements Serializable {
    private Request request;

    public <T extends Request> RequestDTO(T request) {
        this.request = request;
    }

    public Request getRequest() {
        return request;
    }
}
