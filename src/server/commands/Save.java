package server.commands;

import common.requests.Request;
import common.requests.RequestDTO;
import common.requests.SaveRequest;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;

import java.io.IOException;

public class Save extends Command implements CommandWithoutParameters {
    public Save(String consoleName) {
        super(consoleName, "Сохранить коллекцию (в тот же файл)", "Коллекция сохранена!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        fileManager.write(collectionManager.getCollection(), collectionManager.getCollectionFilename());
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
