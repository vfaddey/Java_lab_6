package commands;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import commands.interfaces.CommandWithParameters;
import managers.Validator;
import model.Organization;

public class FilterLessThanAnnualTurnover extends Command implements CommandWithParameters {
    public FilterLessThanAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "Выводит все элементы, оборот которых меньше указанного", "");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException {
        if (Validator.isCorrectNumber(parameters[0], Long.class)) {
            long annualTurnover = Long.parseLong(parameters[0]);
            Organization[] elements = collectionManager.getElementsLessThanAnnualTurnover(annualTurnover);
            for (Organization el : elements) {
                collectionManager.getConsoleHandler().print(el.toString());
            }
        } else {
            throw new WrongParameterException("Неверно введен параметр!");
        }
    }
}
