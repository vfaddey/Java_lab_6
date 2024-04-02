package server.commands;

import common.exceptions.WrongParameterException;
import common.requests.FilterLessThanAnnualTurnoverRequest;
import common.requests.RequestDTO;
import common.requests.ShowRequest;
import common.responses.Response;
import common.responses.ShowResponse;
import server.interfaces.CommandWithParameters;
import server.managers.MessageType;
import server.managers.Validator;
import common.model.Organization;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class FilterLessThanAnnualTurnover extends Command implements CommandWithParameters {
    public FilterLessThanAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "<long annualTurnover> Выводит все элементы, оборот которых меньше указанного", "");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        if (Validator.isCorrectNumber(parameters[0], Long.class)) {
            long annualTurnover = Long.parseLong(parameters[0]);
            Organization[] elements = collectionManager.getElementsLessThanAnnualTurnover(annualTurnover);
            for (Organization el : elements) {
                collectionManager.getSender().send(el, MessageType.DEFAULT);
            }
        } else {
            throw new WrongParameterException("Неверно введен параметр!");
        }
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        FilterLessThanAnnualTurnoverRequest request = (FilterLessThanAnnualTurnoverRequest) requestDTO.getRequest();
        LinkedList<Organization> elements = new LinkedList<>(List.of(collectionManager.getElementsLessThanAnnualTurnover(request.getAnnualTurnover())));
        ShowResponse response = new ShowResponse(getNameInConsole(), successPhrase);
        response.setOrganizations(elements);
        return response;
    }
}
