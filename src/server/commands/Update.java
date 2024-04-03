package server.commands;

import common.exceptions.ElementNotFoundException;
import common.exceptions.WrongParameterException;
import common.requests.RequestDTO;
import common.requests.UpdateRequest;
import common.responses.ErrorResponse;
import common.responses.Response;
import common.responses.SuccessResponse;
import server.interfaces.CommandWithParameters;
import server.managers.MessageType;
import server.managers.Validator;
import common.model.Address;
import common.model.Coordinates;
import common.model.Organization;
import common.model.OrganizationType;

import java.io.IOException;
import java.util.NoSuchElementException;

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


            String answer = collectionManager.getSender().ask("", MessageType.WHAT_TO_CHANGE);
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
            collectionManager.getSender().send(e.toString(), MessageType.ERROR);
            collectionManager.getSender().send("Введите id существующего элемента. Используйте команду show для просмотра коллекции.", MessageType.ADVICE);
        } catch (WrongParameterException e) {
            collectionManager.getSender().send(e.toString(), MessageType.ERROR);
            execute(parameters);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Response execute(RequestDTO requestDTO) throws IOException {
        UpdateRequest request = (UpdateRequest) requestDTO.getRequest();
        String name;
        Coordinates coordinates;
        long annualTurnover;
        int employeesCount;
        OrganizationType type;
        Address address;

        try {
            Organization oldElement = collectionManager.getElementById(request.getId());

            if (!request.getName().isEmpty()) {
                name = request.getName();
            } else {
                name = oldElement.getName();
            }
            if (request.getCoordinates() != null) {
                coordinates = request.getCoordinates();
            } else {
                coordinates = oldElement.getCoordinates();
            }
            if (request.getAnnualTurnover() != 0) {
                annualTurnover = request.getAnnualTurnover();
            } else {
                annualTurnover = oldElement.getAnnualTurnover();
            }
            if (request.getEmployeesCount() != 0) {
                employeesCount = request.getEmployeesCount();
            } else {
                employeesCount = oldElement.getEmployeesCount();
            }
            if (request.getOrganizationType() != null) {
                type = request.getOrganizationType();
            } else {
                type = oldElement.getType();
            }
            if (request.getAddress() != null) {
                address = request.getAddress();
            } else {
                address = oldElement.getOfficialAddress();
            }

            Organization newOrganization = new Organization(
                    oldElement.getId(),
                    name,
                    coordinates,
                    oldElement.getCreationDate(),
                    annualTurnover,
                    employeesCount,
                    type,
                    address);
            collectionManager.setElementById(oldElement.getId(), newOrganization);
            return new SuccessResponse(getNameInConsole(), successPhrase);
        } catch (ElementNotFoundException e) {
            return new ErrorResponse(e.toString());
        }
    }
}
