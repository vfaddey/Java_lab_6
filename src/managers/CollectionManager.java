package managers;

import exceptions.ElementNotFoundException;
import exceptions.NullUserRequestException;
import exceptions.WrongParameterException;
import model.*;


import java.time.LocalDate;
import java.util.*;


/**
 * Class, that manage the collection, makes requests to user
 */
public class CollectionManager {

    private LinkedList<Organization> collection;
    private String collectionFilename;
    private final ConsoleHandler consoleHandler;
    private String information;
    private LocalDate lastUpdateDate;

    public CollectionManager(ConsoleHandler consoleHandler) {
        this.consoleHandler = consoleHandler;
        lastUpdateDate = LocalDate.now();
        loadCollection();
        updateInformation();
    }

    public void loadCollection() {
        String fileName = consoleHandler.collectionFilenameRequest();
        this.collection = FileManager.readCollectionFromCSV(fileName, consoleHandler);
        if (collection == null) {
            loadCollection();
        }
        this.collectionFilename = fileName;
        if (collection != null) {
            Collections.sort(collection);
        }
    }

    private void updateInformation() {
        information = "Тип коллекции: " + LinkedList.class.getName() + "\n"
                + "Хранит объекты типа: " + getCollectionClassName() + "\n"
                + "Последнее обновление коллекции: " + lastUpdateDate;
    }

    private String getCollectionClassName() {
        Class<? extends LinkedList> dataType = collection.getClass();
        return dataType.getName();
    }

    public void addNewElement(Organization organization) {
        collection.add(organization);
        lastUpdateDate = LocalDate.now();
    }

    public Organization interactiveOrganizationCreation() {
        return new Organization(
                (long) (Math.random() * Long.MAX_VALUE),
                nameRequest(),
                coordinatesRequest(),
                LocalDate.now(),
                annualTurnoverRequest(),
                employeesCountRequest(),
                organizationTypeRequest(),
                officialAddressRequest());
    }

    public void organizationCreationFromFile(String... parameters) {
        Organization organization = new Organization(
                (long) (Math.random() * Long.MAX_VALUE),
                parameters[0],
                new Coordinates(Integer.parseInt(parameters[1]), Long.parseLong(parameters[2])),
                LocalDate.now(),
                Long.parseLong(parameters[3]),
                Integer.parseInt(parameters[4]),
                OrganizationType.values()[Integer.parseInt(parameters[5])-1],
                new Address(
                        parameters[6],
                        new Location(
                                Double.parseDouble(parameters[7]),
                                Double.parseDouble(parameters[8]),
                                Long.parseLong(parameters[9]))));
        collection.add(organization);
        lastUpdateDate = LocalDate.now();
    }

    public void removeById(long id) throws ElementNotFoundException {
        collection.remove(getElementById(id));
        lastUpdateDate = LocalDate.now();
    }

    public String nameRequest() {
        String name = consoleHandler.ask("Введите название организации: ");
        try {
            if (Validator.isValidName(name)) {
                return name;
            } else {
                throw new WrongParameterException("Имя не может быть пустым");
            }
        } catch (WrongParameterException e) {
            consoleHandler.printError(e.toString());
            return nameRequest();
        }
    }

    public Coordinates coordinatesRequest() {
        String response = consoleHandler.ask("Введите через пробел координаты x и y (числа целые): ");
        int x;
        long y;
        try {
            if (response.split(" ").length < 2) {
                throw new WrongParameterException("Введены не все параметры.");
            }
            if (Validator.isCorrectNumber(response.split(" ")[0], Integer.class) && Validator.isCorrectNumber(response.split(" ")[1], Long.class)) {
                if (!Validator.isNull(response.split(" ")[0])) {
                    x = Integer.parseInt(response.split(" ")[0]);
                    y = Long.parseLong(response.split(" ")[1]);
                    return new Coordinates(x,y);
                } else {
                    throw new WrongParameterException("x не может быть null.");
                }
            } else {
                throw new WrongParameterException("Неверно введены числа.");
            }
        } catch (WrongParameterException e) {
            consoleHandler.printError(e.toString());
            return coordinatesRequest();
        }

    }

    public long annualTurnoverRequest() {
        long result = -1;
        String response = consoleHandler.ask("Введите годовой оборот компании (целое число): ");

        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (response.contains(" ")) {
                String[] splitted = response.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Long.class)) {
                    result = Long.parseLong(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(response, Long.class)) {
                result = Long.parseLong(response);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                return result;
            } else {
                throw new WrongParameterException("Годовой оборот не может быть меньше нуля.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return annualTurnoverRequest();
        }
    }

    public int employeesCountRequest()  {
        int result = -1;
        String response = consoleHandler.ask("Введите количество сотрудников: ");

        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Введена пустая строка");
            }
            if (response.contains(" ")) {
                String[] splitted = response.split(" ");
                if (Validator.isCorrectNumber(splitted[0], Integer.class)) {
                    result = Integer.parseInt(splitted[0]);
                }
            } else if (Validator.isCorrectNumber(response, Integer.class)) {
                result = Integer.parseInt(response);
            } else {
                throw new WrongParameterException("Неверно введено число.");
            }
            if (result > 0) {
                return result;
            } else {
                throw new WrongParameterException("Число сотрудников не может быть меньше одного.");
            }
        } catch (WrongParameterException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return employeesCountRequest();
        }
    }


    public OrganizationType organizationTypeRequest() {
        String response = consoleHandler.askOrganizationType(OrganizationType.values());
        String num;
        try {
            if (Validator.isNull(response) || Validator.isEmptyArray(response.split(" "))) {
                throw new NullUserRequestException("Поле не может быть пустым.");
            }
            if (response.contains(" ")) {
                num = response.split(" ")[0];
                if (Validator.isNull(num)) {
                    throw new WrongParameterException("Строка введена неверно.");
                }

            } else {
                num = response;
            }
            if (Validator.isCorrectNumber(num, Integer.class)) {
                if (Integer.parseInt(num) <= OrganizationType.values().length && Integer.parseInt(num) >= 1) {
                    return OrganizationType.values()[Integer.parseInt(num)-1];
                } else {
                    throw new WrongParameterException("Введено неверный номер.");
                }
            } else {
                throw new WrongParameterException("Неправильно введено число.");
            }

        } catch (WrongParameterException | NumberFormatException | NullUserRequestException e) {
            consoleHandler.printError(e.toString());
            return organizationTypeRequest();
        }
    }

    public Address officialAddressRequest() {
        String zipCode = consoleHandler.ask("Введите город(?): ");
        String loc = consoleHandler.ask("Введите координаты локации x, y, z через пробел (x и y - вещественные, z - целое): ");
        try {
            if (loc.split(" ").length < 3) {
                throw new WrongParameterException("Неверно введены координаты локации");
            }
            if (Validator.isStringWithNumbers(loc) && Validator.isValidName(zipCode)) {
                if (Validator.areCorrectLocationParams(loc.split(" ")[0], loc.split(" ")[1], loc.split(" ")[2])) {
                    double x = Double.parseDouble(loc.split(" ")[0]);
                    double y = Double.parseDouble(loc.split(" ")[1]);
                    long z = Long.parseLong(loc.split(" ")[2]);
                    return new Address(zipCode, new Location(x,y,z));
                } else {
                    throw new WrongParameterException("Неверно введены координаты локации.");
                }
            } throw new WrongParameterException("Неверно введены параметры. Попробуйте снова.");
        } catch (WrongParameterException e) {
            consoleHandler.printError(e.toString());
            return officialAddressRequest();
        }
    }

    public LinkedList<Organization> getCollection() {
        return collection;
    }
    public void clearCollection() {
        this.collection.clear();
    }

    public String getCollectionFilename() {
        return collectionFilename;
    }

    public Organization getElementById(long id) throws ElementNotFoundException {
        for (Organization organization : collection) {
            if (organization.getId() == id) return organization;
        }
        throw new ElementNotFoundException("Элемента с таким id не существует");
    }

    public Organization[] getElementsByName(String substring) {
        List<Organization> elements = new ArrayList<>();
        for (Organization organization : collection) {
            if (Validator.isSubstring(substring, organization.getName())) {
                elements.add(organization);
            }
        }
        return elements.toArray(new Organization[0]);
    }

    public Organization[] getElementsLessThanAnnualTurnover(long annualTurnover) {
        List<Organization> elements = new ArrayList<>();
        for (Organization organization : collection) {
            if (organization.getAnnualTurnover() < annualTurnover) {
                elements.add(organization);
            }
        }
        return elements.toArray(new Organization[0]);
    }

    public void shuffleCollection() {
        Collections.shuffle(collection);
        lastUpdateDate = LocalDate.now();
    }

    public Organization[] getElementsByAnnualTurnover(long annualTurnover) {
        List<Organization> elements = new ArrayList<>();
        for (Organization organization : collection) {
            if (organization.getAnnualTurnover() == annualTurnover) {
                elements.add(organization);
            }
        }
        return elements.toArray(new Organization[0]);
    }

    public void setElementById(long id, Organization element) {
        for (int i = 0; i < collection.size(); i++) {
            if (collection.get(i).getId() == id) {
                collection.set(i, element);
                break;
            }
        }
    }

    public ConsoleHandler getConsoleHandler() {
        return consoleHandler;
    }

    public String getInformation() {
        return information;
    }
}
