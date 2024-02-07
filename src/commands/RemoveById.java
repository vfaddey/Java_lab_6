package commands;

import exceptions.ElementNotFoundException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;

public class RemoveById extends Command implements CommandWithParameters {
    public RemoveById(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Удаляет элемент коллеции по id", "Элемент коллекции удален!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, ElementNotFoundException {
        if (parameters[0].isEmpty()) throw new WrongParameterException("Параметр пуст.");
        try {
            collectionManager.getCollection().remove(collectionManager.getElementById(Long.parseLong(parameters[0])));
            printSuccess();
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Параметр введен неверно.");
        } catch (ElementNotFoundException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
        }

    }
}
