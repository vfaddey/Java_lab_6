package server.commands;


import common.requests.AddRequest;
import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;
import common.model.Organization;

import java.time.LocalDate;

public class RemoveGreater extends Command implements CommandWithoutParameters {
    public RemoveGreater(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Удаляет элементы больше заданного", "Элементы удалены!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        AddRequest request = (AddRequest) requestDTO.getRequest();
        Organization comparator = new Organization(
                (long) (Math.random() * Long.MAX_VALUE),
                request.getName(),
                request.getCoordinates(),
                LocalDate.now(),
                request.getAnnualTurnover(),
                request.getEmployeesCount(),
                request.getOrganizationType(),
                request.getAddress()
        );
        collectionManager.getCollection().removeIf(organization -> comparator.compareTo(organization) < 0);
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
