package server.commands;

import common.exceptions.WrongParameterException;
import server.interfaces.CommandWithParameters;
import server.managers.MessageType;
import server.managers.Validator;
import common.model.Organization;

import java.io.IOException;

public class FilterLessThanAnnualTurnover extends Command implements CommandWithParameters {
    public FilterLessThanAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "<long annualTurnover> Выводит все элементы, оборот которых меньше указанного", "");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        if (Validator.isCorrectNumber(parameters[0], Long.class)) {
            long annualTurnover = Long.parseLong(parameters[0]);
            Organization[] elements = collectionManager.getElementsLessThanAnnualTurnover(annualTurnover);
            for (Organization el : elements) {
                collectionManager.getSender().send(el, MessageType.DEFAULT);
            }
        } else {
            throw new WrongParameterException("Неверно введен параметр!");
        }
    }
}
