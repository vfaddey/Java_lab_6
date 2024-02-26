package commands;

import exceptions.ElementNotFoundException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;

public class RemoveById extends Command implements CommandWithParameters {
    public RemoveById(String consoleName) {
        super(consoleName, "<long id> Удаляет элемент коллеции по id", "Элемент коллекции удален!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        if (parameters[0].isEmpty()) throw new WrongParameterException("Параметр пуст.");
        try {
            collectionManager.removeById(Long.parseLong(parameters[0]));
            printSuccess();
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Параметр введен неверно.");
        } catch (ElementNotFoundException e) {
            collectionManager.getSender().getConsoleHandler().printError(e.toString());
        }
    }
}
