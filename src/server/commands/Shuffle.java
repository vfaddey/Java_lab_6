package server.commands;

import common.requests.RequestDTO;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithoutParameters;

public class Shuffle extends Command implements CommandWithoutParameters {
    public Shuffle(String nameInConsole) {
        super(nameInConsole, "<Без параметров> Перемешивает элементы в коллекции", "Элементы коллекции перемешаны!");
    }

    @Override
    public Response execute(RequestDTO requestDTO) {
        collectionManager.shuffleCollection();
        return new SuccessResponse(getNameInConsole(), successPhrase);
    }
}
