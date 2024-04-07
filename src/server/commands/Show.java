package server.commands;

import common.requests.RequestDTO;
import common.requests.ShowRequest;
import common.responses.EmptyResponse;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.ShowResponse;
import server.interfaces.CommandWithoutParameters;
import common.model.Organization;

import java.util.LinkedList;


public class Show extends Command implements CommandWithoutParameters {

    public Show(String consoleName) {
        super(consoleName, "<int количество> Выводит коллекцию", "");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        ShowRequest request = (ShowRequest) requestDTO.getRequest();
        if (request != null) {
            if (request.getQuantity() < 0) {
                ShowResponse response = new ShowResponse(getNameInConsole(), successPhrase);
                response.setOrganizations(collectionManager.getCollection());
                return response;
            } else {
                LinkedList<Organization> organizationsToSend = new LinkedList<>();
                if (request.getQuantity() <= collectionManager.getCollection().size()) {
                    for (int i = 0; i < request.getQuantity(); i++) {
                        organizationsToSend.add(collectionManager.getCollection().get(i));
                    }
                    ShowResponse response = new ShowResponse(getNameInConsole(), successPhrase);
                    response.setOrganizations(organizationsToSend);
                    return response;
                } else {
                    return new ErrorResponse("В коллекции нет столько элементов");
                }
            }
        } else {
            return new EmptyResponse();
        }
    }
}
