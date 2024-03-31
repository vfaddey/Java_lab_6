package server.commands;

import common.requests.Request;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;

import java.io.IOException;

public class Clear extends Command implements CommandWithoutParameters {
    public Clear(String consoleName) {
        super(consoleName, "<Без параметров> Очистить коллекцию", "Коллекция очищена!");
    }

    @Override
    public void execute() throws IOException {
        collectionManager.clearCollection();
        printSuccess();
    }

    @Override
    public Response execute(Request request) throws IOException {
        collectionManager.clearCollection();
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
