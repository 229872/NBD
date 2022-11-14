package repositories;

import java.util.List;
import java.util.UUID;

public interface Repository <T> {
    void add(T item);
    void remove(T item);
    T find(UUID uuid);
    List<T> findAll();
}
