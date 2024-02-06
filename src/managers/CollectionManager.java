package managers;

import exceptions.ElementNotFoundException;
import model.*;


import java.time.LocalDate;
import java.util.LinkedList;

public class CollectionManager {

    private LinkedList<Organization> collection;
    private String collectionFilename;
    private ConsoleHandler consoleHandler;
    private String information;

    public CollectionManager(LinkedList<Organization> collection, String collectionFilename, ConsoleHandler consoleHandler) {
        this.collection = collection;
        this.collectionFilename = collectionFilename;
        this.consoleHandler = consoleHandler;
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
        System.out.println("Коллекция создана!");
        System.out.println(organization);
    }

    private String nameRequest() {
        return consoleHandler.askName();
    }

    private Coordinates coordinatesRequest() {
        return consoleHandler.askCoordinates();
    }

    private long annualTurnoverRequest() {
        return consoleHandler.askAnnualTurnover();
    }

    private int employeesCountRequest() {
        return consoleHandler.askEmployeesCount();
    }

    private OrganizationType organizationTypeRequest() {
        String response = consoleHandler.askOrganizationType();
        return OrganizationType.values()[Integer.parseInt(response)-1];
    }

    private Address officialAddressRequest() {
        return consoleHandler.askOfficialAddress();
    }

    public LinkedList<Organization> getCollection() {
        return collection;
    }

    public String getCollectionFilename() {
        return collectionFilename;
    }

    public Organization getElementById(long id) {
        for (Organization organization : collection) {
            if (organization.getId() == id) return organization;
        }
        throw new ElementNotFoundException("Элемента с таким id не существует");
    }

    public ConsoleHandler getConsoleHandler() {
        return consoleHandler;
    }

    public String getInformation() {
        return information;
    }
}
