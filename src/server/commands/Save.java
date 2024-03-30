package server.commands;

import common.Requests.Request;
import common.Requests.SaveRequest;
import common.Responses.Response;
import common.Responses.SuccessResponse;
import common.exceptions.IncorrectFilenameException;
import server.interfaces.CommandWithParameters;
import server.interfaces.CommandWithoutParameters;
import server.managers.MessageType;

import java.io.IOException;

public class Save extends Command implements CommandWithoutParameters {
    public Save(String consoleName) {
        super(consoleName, "Сохранить коллекцию (в тот же файл)", "Коллекция сохранена!");
    }

    @Override
    public void execute() throws IOException {
        fileManager.write(collectionManager.getCollection(), collectionManager.getCollectionFilename());
        printSuccess();
    }


    @Override
    public Response execute(Request request) throws IOException {
        if (request instanceof SaveRequest) {
            execute();
        }
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
