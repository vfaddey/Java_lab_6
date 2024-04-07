package server.interfaces;

import common.model.Organization;

import java.util.LinkedList;

public interface FileManager {
    LinkedList<Organization> read(String filename);
    void write(LinkedList<Organization> collection, String filename);
}
