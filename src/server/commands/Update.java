package server.commands;

import server.exceptions.ElementNotFoundException;
import server.exceptions.WrongParameterException;
import server.interfaces.CommandWithParameters;
import server.managers.Validator;
import server.model.Address;
import server.model.Coordinates;
import server.model.Organization;
import server.model.OrganizationType;

import java.io.IOException;

/**
 * Command, needs to update element of collection by its id. Offers user to write required fields
 */
public class Update extends Command implements CommandWithParameters {
    public Update(String consoleName) {
        super(consoleName, "<long id> Обновляет элемент коллекции по id", "Элемент успешно обновлен!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException, IOException {
        try {
            Organization element = collectionManager.getElementById(Long.parseLong(parameters[0]));
            String name = element.getName();
            Coordinates coordinates = element.getCoordinates();
            long annualTurnover = element.getAnnualTurnover();
            int employeesCount = element.getEmployeesCount();
            OrganizationType type = element.getType();
            Address officialAddress = element.getOfficialAddress();


            String answer = collectionManager.getSender().ask("WTC");
            if (Validator.isStringWithIntegers(answer)) {
                String[] splitted = answer.split(" ");
                int[] fieldsNumbers = new int[splitted.length];
                for (int i = 0; i < fieldsNumbers.length; i++) {
                    fieldsNumbers[i] = Integer.parseInt(splitted[i]);
                }
                for (int num : fieldsNumbers) {
                    if (num > 6) {
                        throw new WrongParameterException("Число " + num + " не соответствует ни одному из полей");
                    }
                    switch (num) {
                        case 1 -> name = collectionManager.getSender().nameRequest();
                        case 2 -> coordinates = collectionManager.getSender().coordinatesRequest();
                        case 3 -> annualTurnover = collectionManager.getSender().annualTurnoverRequest();
                        case 4 -> employeesCount = collectionManager.getSender().employeesCountRequest();
                        case 5 -> type = collectionManager.getSender().organizationTypeRequest();
                        case 6 -> officialAddress = collectionManager.getSender().officialAddressRequest();
                    }
                }
                Organization updatedElement = new Organization(
                        element.getId(),
                        name,
                        coordinates,
                        element.getCreationDate(),
                        annualTurnover,
                        employeesCount,
                        type,
                        officialAddress);
                collectionManager.setElementById(Long.parseLong(parameters[0]), updatedElement);
                printSuccess();
            } else throw new WrongParameterException("Строка должна состоять только из чисел и пробелов.");
        } catch (NumberFormatException e) {
            throw new WrongParameterException("Неправильно введен параметр.");
        } catch (ElementNotFoundException e) {
            collectionManager.getSender().send(e.toString());
            collectionManager.getSender().send("Введите id существующего элемента. Используйте команду show для просмотра коллекции.");
        } catch (WrongParameterException e) {
            collectionManager.getSender().send(e.toString());
            execute(parameters);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
