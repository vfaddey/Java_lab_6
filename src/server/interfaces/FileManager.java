package server.interfaces;

import server.managers.Sender;
import server.model.Organization;

import java.util.LinkedList;

public interface FileManager {
    LinkedList<Organization> read(String filename, Sender sender);
    void write(LinkedList<Organization> collection, String filename);
}
