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
