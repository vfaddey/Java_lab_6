package commands;

import exceptions.ElementNotFoundException;
import exceptions.WrongParameterException;
import interfaces.CommandWithParameters;
import managers.CollectionManager;
import managers.Validator;
import model.Address;
import model.Coordinates;
import model.Organization;
import model.OrganizationType;

import java.lang.reflect.Method;

public class Update extends Command implements CommandWithParameters {
    public Update(String consoleName, CollectionManager collectionManager) {
        super(consoleName, collectionManager, "Обновляет элемент коллекции по id", "Элемент успешно обновлен!");
    }

    @Override
    public void execute(String... parameters) throws WrongParameterException {
        try {
            Organization element = collectionManager.getElementById(Long.parseLong(parameters[0]));
            String name = element.getName();
            Coordinates coordinates = element.getCoordinates();
            long annualTurnover = element.getAnnualTurnover();
            int employeesCount = element.getEmployeesCount();
            OrganizationType type = element.getType();
            Address officialAddress = element.getOfficialAddress();


            String answer = collectionManager.getConsoleHandler().askWhatToChange();
            if (Validator.isStringWithIntegers(answer)) {
                String[] splitted = answer.split(" ");
                int[] fieldsNumbers = new int[splitted.length];
                for (int i = 0; i < fieldsNumbers.length; i++) {
                    fieldsNumbers[i] = Integer.parseInt(splitted[i]);
                }
                for (int num : fieldsNumbers) {
                    switch (num) {
                        case 1 -> name = collectionManager.nameRequest();
                        case 2 -> coordinates = collectionManager.coordinatesRequest();
                        case 3 -> annualTurnover = collectionManager.annualTurnoverRequest();
                        case 4 -> employeesCount = collectionManager.employeesCountRequest();
                        case 5 -> type = collectionManager.organizationTypeRequest();
                        case 6 -> officialAddress = collectionManager.officialAddressRequest();
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
            collectionManager.getConsoleHandler().printError(e.toString());
            collectionManager.getConsoleHandler().printAdvice("Введите id существующего элемента. Используйте команду show для просмотра коллекции.");
        }
    }
}
