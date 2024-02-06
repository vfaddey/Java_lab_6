package commands;

import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;

public class RemoveById extends Command implements CommandWithParameters {
    public RemoveById(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Удаляет элемент коллеции по id");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        if (parameters[0].isEmpty()) throw new WrongParameterException("Параметр пуст");
        try {
            collectionManager.getCollection().remove(collectionManager.getElementById(Long.parseLong(parameters[0])));
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Параметр введен неверно");
        }

    }
}
