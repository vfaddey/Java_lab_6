package server.commands;

import exceptions.WrongParameterException;
import server.interfaces.CommandWithParameters;
import server.managers.Validator;
import server.model.Organization;

import java.io.IOException;

public class RemoveAnyByAnnualTurnover extends Command implements CommandWithParameters {
    public RemoveAnyByAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "<long annualTurnover> Удаляет элемент по указанному годовому обороту", "Элемент удален!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        if (Validator.isCorrectNumber(parameters[0], Long.class)) {
            Organization element = collectionManager.getElementsByAnnualTurnover(Long.parseLong(parameters[0]))[0];
            collectionManager.getCollection().remove(element);
            printSuccess();
        } else {
            throw new WrongParameterException("Параметр введен неверно!");
        }
    }
}
