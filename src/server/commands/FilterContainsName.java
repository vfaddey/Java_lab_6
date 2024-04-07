package server.commands;

import common.requests.RequestDTO;
import common.responses.Response;

import java.io.IOException;

public class FilterContainsName extends Command {
    public FilterContainsName(String nameInConsole) {
        super(nameInConsole, "<String name> Выводит все элементы, имена которых содержат указанную подстроку", "");
    }

//    @Override
//    public void execute(String... parameters) throws IOException {
//        Organization[] elements = collectionManager.getElementsByName(parameters[0]);
//        for (Organization el : elements) collectionManager.getSender().send(String.valueOf(el), MessageType.DEFAULT);
//    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        return super.execute(requestDTO);
    }
}
