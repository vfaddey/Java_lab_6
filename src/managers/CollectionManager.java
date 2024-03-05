package managers;

import exceptions.ElementNotFoundException;
import interfaces.FileManager;
import model.*;


import java.io.IOException;
import java.time.LocalDate;
import java.util.*;


/**
 * Class, that manage the collection, makes requests to user
 */
public class CollectionManager{
    private String fileName;
    private LinkedList<Organization> collection;
    private String collectionFilename;
    private String information;
    private LocalDate lastUpdateDate;
    private FileManager fileManager;
    private final Sender sender;

    public CollectionManager(FileManager fileManager, Sender sender, String fileName) throws IOException {
        this.sender = sender;
        this.fileManager = fileManager;
        this.fileName = fileName;
        lastUpdateDate = LocalDate.now();
        loadCollectionFromCSV(fileName);
        updateInformation();
    }

    public CollectionManager(FileManager fileManager, Sender sender) throws IOException {
        this.sender = sender;
        this.fileManager = fileManager;
        lastUpdateDate = LocalDate.now();
        loadCollectionFromCSV();
        updateInformation();
    }

    public void loadCollectionFromCSV() throws IOException {
        String fileName = sender.ask("ASK_FILENAME");
        this.collection = fileManager.read(fileName, sender);
        if (collection == null) {
            loadCollectionFromCSV();
        }
        this.collectionFilename = fileName;
        if (collection != null) {
            Collections.sort(collection);
        }
    }

    public void loadCollectionFromCSV(String fileName) throws IOException {
        this.collection = fileManager.read(fileName, sender);
        if (collection == null) {
            loadCollectionFromCSV();
        }
        this.collectionFilename = fileName;
        if (collection != null) {
            Collections.sort(collection);
        }
    }

    private void updateInformation() {
        information = "Тип коллекции: " + LinkedList.class.getName() + "\n"
                + "Хранит объекты типа: " + Organization.class.getName() + "\n"
                + "Количество элементов коллекции: " + collection.size() + "\n"
                + "Последнее обновление коллекции: " + lastUpdateDate;
    }

    public void addNewElement(Organization organization) {
        collection.add(organization);
        lastUpdateDate = LocalDate.now();
    }

    public Organization interactiveOrganizationCreation() throws IOException {
        return new Organization(
                (long) (Math.random() * Long.MAX_VALUE),
                sender.nameRequest(),
                sender.coordinatesRequest(),
                LocalDate.now(),
                sender.annualTurnoverRequest(),
                sender.employeesCountRequest(),
                sender.organizationTypeRequest(),
                sender.officialAddressRequest());
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


    public String getInformation() {
        return information;
    }

    public void setFileManager(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    public Sender getSender() {
        return sender;
    }
}
