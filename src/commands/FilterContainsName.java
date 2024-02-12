package commands;

import commands.interfaces.CommandWithParameters;
import model.Organization;

public class FilterContainsName extends Command implements CommandWithParameters {
    public FilterContainsName(String nameInConsole) {
        super(nameInConsole, "Выводит все элементы, имена которых содержат указанную подстроку", "");
    }

    @Override
    public void execute(String... parameters) {
        Organization[] elements = collectionManager.getElementsByName(parameters[0]);
        for (Organization el : elements) collectionManager.getConsoleHandler().println(el);
    }
}
