package server.commands;

import common.exceptions.ElementNotFoundException;
import common.exceptions.WrongParameterException;
import common.requests.RemoveByIdRequest;
import common.requests.Request;
import common.requests.RequestDTO;
import common.responses.EmptyResponse;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithParameters;
import server.managers.MessageType;

import java.io.IOException;

public class RemoveById extends Command {
    public RemoveById(String consoleName) {
        super(consoleName, "<long id> Удаляет элемент коллеции по id", "Элемент коллекции удален!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        RemoveByIdRequest request = (RemoveByIdRequest) requestDTO.getRequest();
        if (request != null) {
            try {
                collectionManager.removeById(request.getId());
                return new SuccessResponse(getNameInConsole(), successPhrase);
            } catch (ElementNotFoundException e) {
                return new ErrorResponse(e.toString());
            }
        } else {
            return new EmptyResponse();
        }
    }
}
