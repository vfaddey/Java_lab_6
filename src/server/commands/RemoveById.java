package server.commands;

import server.exceptions.ElementNotFoundException;
import server.exceptions.WrongParameterException;
import server.interfaces.CommandWithParameters;
import server.managers.MessageType;

import java.io.IOException;

public class RemoveById extends Command implements CommandWithParameters {
    public RemoveById(String consoleName) {
        super(consoleName, "<long id> Удаляет элемент коллеции по id", "Элемент коллекции удален!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        if (parameters[0].isEmpty()) throw new WrongParameterException("Параметр пуст.");
        try {
            collectionManager.removeById(Long.parseLong(parameters[0]));
            printSuccess();
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Параметр введен неверно.");
        } catch (ElementNotFoundException e) {
            collectionManager.getSender().send(e.toString(), MessageType.ERROR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
