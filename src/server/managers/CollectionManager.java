package server.managers;

import client.managers.Validator;
import common.exceptions.ElementNotFoundException;
import server.interfaces.FileManager;
import common.model.*;


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

    public CollectionManager(FileManager fileManager, String fileName) throws IOException {
        this.fileManager = fileManager;
        this.fileName = fileName;
        lastUpdateDate = LocalDate.now();
        loadCollectionFromCSV(fileName);
        updateInformation();
    }


    public void loadCollectionFromCSV(String fileName) {
        this.collection = fileManager.read(fileName);
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

    public void removeById(long id) throws ElementNotFoundException {
        collection.removeIf(org -> org.getId() == id);
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
}
