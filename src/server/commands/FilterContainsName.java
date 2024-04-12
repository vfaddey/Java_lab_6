package server.commands;

import common.model.Organization;
import common.requests.FilterContainsNameRequest;
import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.ShowResponse;

import java.io.IOException;
import java.util.LinkedList;

public class FilterContainsName extends Command {
    public FilterContainsName(String nameInConsole) {
        super(nameInConsole, "<String name> Выводит все элементы, имена которых содержат указанную подстроку", "");
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        FilterContainsNameRequest request = (FilterContainsNameRequest) requestDTO.getRequest();
        LinkedList<Organization> elements = collectionManager.getElementsByName(request.getSubstring());
        ShowResponse response = new ShowResponse(getNameInConsole(), "");
        response.setOrganizations(elements);
        return response;
    }
}
