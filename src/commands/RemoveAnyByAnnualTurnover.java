package commands;

import exceptions.WrongParameterException;
import commands.interfaces.CommandWithParameters;
import managers.Validator;
import model.Organization;

public class RemoveAnyByAnnualTurnover extends Command implements CommandWithParameters {
    public RemoveAnyByAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "Удаляет элемент по указанному годовому обороту", "Элемент удален!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        if (Validator.isCorrectNumber(parameters[0], Long.class)) {
            Organization element = collectionManager.getElementsByAnnualTurnover(Long.parseLong(parameters[0]))[0];
            collectionManager.getCollection().remove(element);
            printSuccess();
        } else {
            throw new WrongParameterException("Параметр введен неверно!");
        }
    }
}
