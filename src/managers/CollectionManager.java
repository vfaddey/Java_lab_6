package managers;

import exceptions.ElementNotFoundException;
import model.*;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class CollectionManager {

    private final LinkedList<Organization> collection;
    private final String collectionFilename;
    private final ConsoleHandler consoleHandler;
    private String information;
    private LocalDate lastUpdateDate;

    public CollectionManager(LinkedList<Organization> collection, String collectionFilename, ConsoleHandler consoleHandler) {
        this.collection = collection;
        this.collectionFilename = collectionFilename;
        this.consoleHandler = consoleHandler;
        lastUpdateDate = LocalDate.now();

    }

    private void updateInformation() {
        information = "Тип коллекции: " + LinkedList.class.getName() + "\n"
                + "Хранит объекты типа: " + collection.getFirst().getClass().getName() + "\n"
                + "Последнее обновление коллекции: " + lastUpdateDate;
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
