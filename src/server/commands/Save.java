package server.commands;

import server.exceptions.IncorrectFilenameException;
import server.interfaces.CommandWithParameters;
import server.interfaces.CommandWithoutParameters;
import server.managers.MessageType;

import java.io.IOException;

public class Save extends Command implements CommandWithoutParameters, CommandWithParameters {
    public Save(String consoleName) {
        super(consoleName, "(необязательно <Путь к файлу>) Сохранить коллекцию (в тот же файл)", "Коллекция сохранена!");
    }

    @Override
    public void execute() throws IOException {
        fileManager.write(collectionManager.getCollection(), collectionManager.getCollectionFilename());
        printSuccess();
    }

    @Override
    public void execute(String... parameters) throws IOException {
        if (parameters.length == 0) execute();
        else {
            try {
                if (parameters[0].matches("^\\D.*")) {
                    if (parameters[0].matches(".*\\.csv$")) {
                        String filename = parameters[0];
                        fileManager.write(collectionManager.getCollection(), filename);
                    } else throw new IncorrectFilenameException("Расширение файла должно быть .csv");
                } else throw new IncorrectFilenameException("Строка не должна начинаться с числа");
            } catch (IncorrectFilenameException e) {
                collectionManager.getSender().send(e.toString(), MessageType.ERROR);
            }
        }
    }
}
