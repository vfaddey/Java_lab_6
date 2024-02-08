package commands;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;
import managers.Validator;
import model.Organization;

public class FilterLessThanAnnualTurnover extends Command implements CommandWithParameters {
    public FilterLessThanAnnualTurnover(String nameInConsole) {
        super(nameInConsole, "Выводит все элементы, оборот которых меньше указанного", "");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException {
        if (Validator.isCorrectLong(parameters[0])) {
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
