package commands;

import exceptions.*;
import interfaces.CommandWithParameters;
import interfaces.CommandWithoutParameters;
import managers.Validator;
import model.Organization;


public class Show extends Command implements CommandWithoutParameters, CommandWithParameters {

    public Show(String consoleName) {
        super(consoleName, "<int количество> Выводит коллекцию", "");
    }

    @Override
    public void execute() {
        if (!collectionManager.getCollection().isEmpty()) {
            for (Organization organization : collectionManager.getCollection()) {
                collectionManager.getSender().getConsoleHandler().println(organization);
            }
        } else {
            collectionManager.getSender().getConsoleHandler().println("В коллекции пока нет элементов(");
        }

    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
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
                        collectionManager.getSender().getConsoleHandler().println(collectionManager.getCollection().get(i));
                    }
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
