package server.commands;

import common.requests.Request;
import common.requests.RequestDTO;
import common.responses.InfoResponse;
import common.responses.Response;
import server.interfaces.CommandWithoutParameters;
import server.managers.MessageType;

import java.io.IOException;

public class Info extends Command implements CommandWithoutParameters {
    public Info(String consoleName) {
        super(consoleName, "<без параметров> Вывести информацию о коллекции", "");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        return new InfoResponse(getNameInConsole(), collectionManager.getInformation());
    }
}
