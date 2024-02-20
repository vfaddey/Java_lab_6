package interfaces;

import exceptions.ElementNotFoundException;

import java.util.LinkedList;
import java.util.Optional;

public interface Dao<T> {

    Optional<T> get(long id) throws ElementNotFoundException;

    LinkedList<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
