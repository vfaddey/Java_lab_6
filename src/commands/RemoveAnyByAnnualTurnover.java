package commands;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;
import managers.Validator;
import model.Organization;

public class RemoveAnyByAnnualTurnover extends Command implements CommandWithParameters {
    public RemoveAnyByAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "Удаляет элемент по указанному годовому обороту", "Элемент удален!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException {
        if (Validator.isCorrectLong(parameters[0])) {
            Organization element = collectionManager.getElementsByAnnualTurnover(Long.parseLong(parameters[0]))[0];
            collectionManager.getCollection().remove(element);
            printSuccess();
        } else {
            throw new WrongParameterException("Параметр введен неверно!");
        }
    }
}
