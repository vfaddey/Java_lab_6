package interfaces;

import managers.ConsoleHandler;
import model.Organization;

import java.util.LinkedList;

public interface FileManager {
    LinkedList<Organization> read(String filename, ConsoleHandler consoleHandler);
    void write(LinkedList<Organization> collection, String filename);
}
