package server.commands;

import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;

public class Save extends Command implements CommandWithoutParameters {
    public Save(String consoleName) {
        super(consoleName, "Сохранить коллекцию (в тот же файл)", "Коллекция сохранена!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        fileManager.write(collectionManager.getCollection(), collectionManager.getCollectionFilename());
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
