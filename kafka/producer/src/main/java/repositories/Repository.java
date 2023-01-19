package repositories;

import model.UniqueId;

import java.util.List;
import java.util.UUID;

public interface Repository <T> {
    void add(T item);
    void remove(T item);

    void update(UniqueId uuid, T item);
    T find(UniqueId id);
    List<T> findAll();
}
