package server.commands;

import common.requests.FilterLessThanAnnualTurnoverRequest;
import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.ShowResponse;
import common.model.Organization;

import java.util.LinkedList;
import java.util.List;

public class FilterLessThanAnnualTurnover extends Command {
    public FilterLessThanAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "<long annualTurnover> Выводит все элементы, оборот которых меньше указанного", "");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        FilterLessThanAnnualTurnoverRequest request = (FilterLessThanAnnualTurnoverRequest) requestDTO.getRequest();
        LinkedList<Organization> elements = new LinkedList<>(List.of(collectionManager.getElementsLessThanAnnualTurnover(request.getAnnualTurnover())));
        ShowResponse response = new ShowResponse(getNameInConsole(), successPhrase);
        response.setOrganizations(elements);
        return response;
    }
}
