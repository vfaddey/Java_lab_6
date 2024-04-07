package server.commands;

import common.exceptions.WrongParameterException;
import common.requests.RemoveAnyByAnnualTurnoverRequest;
import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithParameters;
import client.managers.Validator;
import common.model.Organization;

import java.io.IOException;

public class RemoveAnyByAnnualTurnover extends Command {
    public RemoveAnyByAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "<long annualTurnover> Удаляет элемент по указанному годовому обороту", "Элемент удален!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        RemoveAnyByAnnualTurnoverRequest request = (RemoveAnyByAnnualTurnoverRequest) requestDTO.getRequest();
        Organization element = collectionManager.getElementsByAnnualTurnover(request.getAnnualTurnover())[0];
        collectionManager.getCollection().remove(element);
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
