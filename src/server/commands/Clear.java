package server.commands;

import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;

public class Clear extends Command implements CommandWithoutParameters {
    public Clear(String consoleName) {
        super(consoleName, "<Без параметров> Очистить коллекцию", "Коллекция очищена!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        collectionManager.clearCollection();
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
