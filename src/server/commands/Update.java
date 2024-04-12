package server.commands;

import common.exceptions.ElementNotFoundException;
import common.requests.RequestDTO;
import common.requests.UpdateRequest;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.SuccessResponse;
import common.model.Address;
import common.model.Coordinates;
import common.model.Organization;
import common.model.OrganizationType;

/**
 * Command, needs to update element of collection by its id. Offers user to write required fields
 */
public class Update extends Command {
    public Update(String consoleName) {
        super(consoleName, "<long id> Обновляет элемент коллекции по id", "Элемент успешно обновлен!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        UpdateRequest request = (UpdateRequest) requestDTO.getRequest();
        String name;
        Coordinates coordinates;
        long annualTurnover;
        int employeesCount;
        OrganizationType type;
        Address address;

        try {
            Organization oldElement = collectionManager.getElementById(request.getId());

            if (!request.getName().isEmpty()) {
                name = request.getName();
            } else {
                name = oldElement.getName();
            }
            if (request.getCoordinates() != null) {
                coordinates = request.getCoordinates();
            } else {
                coordinates = oldElement.getCoordinates();
            }
            if (request.getAnnualTurnover() != 0) {
                annualTurnover = request.getAnnualTurnover();
            } else {
                annualTurnover = oldElement.getAnnualTurnover();
            }
            if (request.getEmployeesCount() != 0) {
                employeesCount = request.getEmployeesCount();
            } else {
                employeesCount = oldElement.getEmployeesCount();
            }
            if (request.getOrganizationType() != null) {
                type = request.getOrganizationType();
            } else {
                type = oldElement.getType();
            }
            if (request.getAddress() != null) {
                address = request.getAddress();
            } else {
                address = oldElement.getOfficialAddress();
            }

            Organization newOrganization = new Organization(
                    oldElement.getId(),
                    name,
                    coordinates,
                    oldElement.getCreationDate(),
                    annualTurnover,
                    employeesCount,
                    type,
                    address);
            collectionManager.setElementById(oldElement.getId(), newOrganization);
            return new SuccessResponse(getNameInConsole(), successPhrase);
        } catch (ElementNotFoundException e) {
            return new ErrorResponse(e.toString());
        }
    }
}
