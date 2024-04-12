package server.commands;

import common.requests.AddRequest;
import common.requests.RequestDTO;
import common.responses.EmptyResponse;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;
import common.model.Organization;

import java.time.LocalDate;

/**
 * Adds a new element to collection
 */
public class Add extends Command implements CommandWithoutParameters {
    public Add(String consoleName) {
        super(consoleName, "<Без параметров> Добавить новую организацию в коллекцию", "Новый элемент добавлен!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        AddRequest request = (AddRequest) requestDTO.getRequest();
        if (request != null) {
            Organization organization = new Organization(
                    (long) (Math.random() * Long.MAX_VALUE),
                    request.getName(),
                    request.getCoordinates(),
                    LocalDate.now(),
                    request.getAnnualTurnover(),
                    request.getEmployeesCount(),
                    request.getOrganizationType(),
                    request.getAddress()
            );
            collectionManager.addNewElement(organization);
            return new SuccessResponse(getNameInConsole(), successPhrase);
        }
        return new EmptyResponse();
    }
}
