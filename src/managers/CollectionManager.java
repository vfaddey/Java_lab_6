package managers;

import exceptions.CommandNotExistsException;
import exceptions.ElementNotFoundException;
import exceptions.IncorrectFilenameException;
import exceptions.WrongParameterException;
import model.*;


import java.lang.reflect.Type;
import java.lang.reflect.ParameterizedType;
import java.time.LocalDate;
import java.util.*;

public class CollectionManager {

    private LinkedList<Organization> collection;
    private String collectionFilename;
    private final ConsoleHandler consoleHandler;
    private String information;
    private LocalDate lastUpdateDate;

    public CollectionManager(ConsoleHandler consoleHandler) throws IncorrectFilenameException, ElementNotFoundException, WrongParameterException, CommandNotExistsException {
        this.consoleHandler = consoleHandler;
        lastUpdateDate = LocalDate.now();
        loadCollection();
        updateInformation();
    }

    public void loadCollection() throws IncorrectFilenameException, ElementNotFoundException, WrongParameterException, CommandNotExistsException {
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
        Type type = collection.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            ParameterizedType paramType = (ParameterizedType) type;
            Type[] typeArguments = paramType.getActualTypeArguments();
            if (typeArguments.length > 0) {
                if (typeArguments[0] instanceof Class) {
                    Class<?> typeArgClass = (Class<?>) typeArguments[0];
                    return typeArgClass.getName();
                }
            }
        }
        return "Не удалось определить тип";
    }

    public void interactiveOrganizationCreation() {
        Organization organization = new Organization(
                (long) (Math.random() * Long.MAX_VALUE),
                nameRequest(),
                coordinatesRequest(),
                LocalDate.now(),
                annualTurnoverRequest(),
                employeesCountRequest(),
                organizationTypeRequest(),
                officialAddressRequest());
        collection.add(organization);
        lastUpdateDate = LocalDate.now();
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
                                Double.parseDouble(parameters[8])
                                , Long.parseLong(parameters[9]))));
        collection.add(organization);
        lastUpdateDate = LocalDate.now();
    }

    public void removeById(long id) throws ElementNotFoundException {
        collection.remove(getElementById(id));
        lastUpdateDate = LocalDate.now();
    }

    public String nameRequest() {
        return consoleHandler.askName();
    }

    public Coordinates coordinatesRequest() {
        return consoleHandler.askCoordinates();
    }

    public long annualTurnoverRequest() {
        return consoleHandler.askAnnualTurnover();
    }

    public int employeesCountRequest() {
        return consoleHandler.askEmployeesCount();
    }

    public OrganizationType organizationTypeRequest() {
        String response = consoleHandler.askOrganizationType();
        return OrganizationType.values()[Integer.parseInt(response)-1];
    }

    public Address officialAddressRequest() {
        return consoleHandler.askOfficialAddress();
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
