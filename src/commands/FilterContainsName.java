package commands;

import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;
import model.Organization;

public class FilterContainsName extends Command implements CommandWithParameters {
    public FilterContainsName(String nameInConsole) {
        super(nameInConsole, "Выводит все элементы, имена которых содержат указанную подстроку", "");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IncorrectFilenameException, ElementNotFoundException {
        Organization[] elements = collectionManager.getElementsByName(parameters[0]);
        for (Organization el : elements) collectionManager.getConsoleHandler().print(el.toString());
    }
}
