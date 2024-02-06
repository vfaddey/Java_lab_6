package commands;

import exceptions.IncorrectFilenameException;
import interfaces.CommandWithParameters;
import interfaces.CommandWithoutParameters;
import managers.CollectionManager;
import managers.FileManager;

public class Save extends Command implements CommandWithoutParameters, CommandWithParameters {
    public Save(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Сохранить коллекцию (в тот же файл)");
    }

    @Override
    public void execute() {
        FileManager.writeCollectionToCSV(collectionManager.getCollection(), collectionManager.getCollectionFilename());
    }

    @Override
    public void execute(String... parameters) throws IncorrectFilenameException {
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
