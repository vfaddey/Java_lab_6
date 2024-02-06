package commands;

import exceptions.ElementNotFoundException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;
import model.Organization;

import java.lang.reflect.Method;

public class Update extends Command implements CommandWithParameters {
    public Update(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Обновляет элемент коллекции по id");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        try {
            Organization element = collectionManager.getElementById(Long.parseLong(parameters[0]));
            Method[] methods = CollectionManager.class.getDeclaredMethods();
            String[] answer = collectionManager.getConsoleHandler().askWhatToChange().split(" ");
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Неправильно введен параметр.");
        } catch (ElementNotFoundException e) {
            collectionManager.getConsoleHandler().printError(e.toString());
            collectionManager.getConsoleHandler().printAdvice("Введите id существующего элемента. Используйте команду show для просмотра коллекции.");
        }
    }
}
