package client.managers;

import common.responses.EmptyResponse;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.SuccessResponse;

public class ResponseHandler {
    public String handleResponse(Response response) {
        if (response instanceof ErrorResponse) {
            return "Ошибка: " + response;
        } else if (response instanceof EmptyResponse) {
            return null;
        } else if (response instanceof SuccessResponse) {
            return "Успешно: " + response;
        }
        return response.toString();
    }
}
