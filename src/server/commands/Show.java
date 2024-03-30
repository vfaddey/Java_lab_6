package server.commands;

import common.requests.Request;
import common.requests.ShowRequest;
import common.responses.EmptyResponse;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.ShowResponse;
import common.exceptions.*;
import server.interfaces.CommandWithParameters;
import server.interfaces.CommandWithoutParameters;
import server.managers.MessageType;
import server.managers.Validator;
import common.model.Organization;

import java.io.IOException;
import java.util.LinkedList;


public class Show extends Command implements CommandWithoutParameters, CommandWithParameters {

    public Show(String consoleName) {
        super(consoleName, "<int количество> Выводит коллекцию", "");
    }

    @Override
    public void execute() throws IOException {
        if (!collectionManager.getCollection().isEmpty()) {
            StringBuilder response = new StringBuilder();
            for (Organization organization : collectionManager.getCollection()) {
                collectionManager.getSender().send(response, MessageType.DEFAULT);
            }
        } else {
            collectionManager.getSender().send("В коллекции пока нет элементов(", MessageType.DEFAULT);
        }

    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        StringBuilder response = new StringBuilder();
        if (parameters.length == 0) {
            execute();
        } else {
            String parameter = parameters[0];
            if (parameter == null) {
                execute();
            } else if (Validator.isCorrectNumber(parameter, Integer.class)) {
                int quantity = Integer.parseInt(parameter);
                if (quantity > 0 && quantity <= collectionManager.getCollection().size()) {
                    for (int i = 0; i < quantity; i++) {
                        if (i != quantity-1) {
                            response.append(collectionManager.getCollection().get(i)).append("\n");
                        } else {
                            response.append(collectionManager.getCollection().get(i));
                        }
                    }
                    collectionManager.getSender().send(response.toString(), MessageType.DEFAULT);
                    System.out.println(response.toString());
                } else if (quantity < 0) {
                    throw new WrongParameterException("Нельзя вывести <= 0 элементов.");
                } else if (quantity > collectionManager.getCollection().size()) {
                    throw new WrongParameterException("Нельзя вывести больше элементов, чем содержится в коллекции (" + collectionManager.getCollection().size() + ").");
                }
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
        }
    }

    @Override
    public Response execute(Request request) {
        if (request instanceof ShowRequest) {
            if (((ShowRequest) request).getQuantity() < 0) {
                ShowResponse response = new ShowResponse(getNameInConsole(), successPhrase);
                response.setOrganizations(collectionManager.getCollection());
                return response;
            } else {
                LinkedList<Organization> organizationsToSend = new LinkedList<>();
                if (((ShowRequest) request).getQuantity() <= collectionManager.getCollection().size()) {
                    for (int i = 0; i < ((ShowRequest) request).getQuantity(); i++) {
                        organizationsToSend.add(collectionManager.getCollection().get(i));
                    }
                    ShowResponse response = new ShowResponse(getNameInConsole(), successPhrase);
                    response.setOrganizations(organizationsToSend);
                    return response;
                } else {
                    return new ErrorResponse("error", "В коллекции нет столько элементов");
                }
            }
        } else {
            return new EmptyResponse();
        }
    }
}
