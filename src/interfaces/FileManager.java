package interfaces;

import managers.ConsoleHandler;
import managers.Sender;
import model.Organization;

import java.util.LinkedList;

public interface FileManager {
    LinkedList<Organization> read(String filename, Sender sender);
    void write(LinkedList<Organization> collection, String filename);
}
