package server.commands;

import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;

import java.io.IOException;

public class Shuffle extends Command implements CommandWithoutParameters {
    public Shuffle(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Перемешивает элементы в коллекции", "Элементы коллекции перемешаны!");
    }

    @Override
    public void execute() throws IOException {
        collectionManager.shuffleCollection();
        printSuccess();
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        execute();
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
