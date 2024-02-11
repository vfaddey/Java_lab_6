package commands;

import exceptions.IncorrectFilenameException;
import commands.interfaces.CommandWithParameters;
import commands.interfaces.CommandWithoutParameters;
import managers.FileManager;

public class Save extends Command implements CommandWithoutParameters, CommandWithParameters {
    public Save(String consoleName) {
        super(consoleName, "Сохранить коллекцию (в тот же файл)", "Коллекция сохранена!");
    }

    @Override
    public void execute() {
        FileManager.writeCollectionToCSV(collectionManager.getCollection(), collectionManager.getCollectionFilename());
        printSuccess();
    }

    @Override
    public void execute(String... parameters) throws IncorrectFilenameException {
        if (parameters.length == 0) execute();
        else {
            try {
                if (parameters[0].matches("^\\D.*")) {
                    if (parameters[0].matches(".*\\.csv$")) {
                        String filename = parameters[0];
                        FileManager.writeCollectionToCSV(collectionManager.getCollection(), filename);
                    } else throw new IncorrectFilenameException("Расширение файла должно быть .csv");
                } else throw new IncorrectFilenameException("Строка не должна начинаться с числа");
            } catch (IncorrectFilenameException e) {
                collectionManager.getConsoleHandler().printError(e.toString());
            }
        }


    }
}
