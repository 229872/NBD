package repositories;

import java.util.List;
import java.util.UUID;

public interface Repository <T> {
    void add(T item);
    void remove(T item);

    void update(UUID id, T item);
    T find(UUID id);
    List<T> findAll();
}
