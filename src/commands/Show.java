package commands;

import exceptions.*;
import interfaces.CommandWithParameters;
import interfaces.CommandWithoutParameters;
import managers.Validator;
import model.Organization;

import java.io.IOException;


public class Show extends Command implements CommandWithoutParameters, CommandWithParameters {

    public Show(String consoleName) {
        super(consoleName, "<int количество> Выводит коллекцию", "");
    }

    @Override
    public void execute() throws IOException {
        if (!collectionManager.getCollection().isEmpty()) {
            StringBuilder response = new StringBuilder();
            for (Organization organization : collectionManager.getCollection()) {
                //response.append(organization).append("\n");
                collectionManager.getSender().send(response);
            }
            //collectionManager.getSender().send(response);
        } else {
            collectionManager.getSender().send("В коллекции пока нет элементов(");
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
                    collectionManager.getSender().send(response.toString());
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
}
