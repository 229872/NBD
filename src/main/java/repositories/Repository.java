package repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Repository<T> {
    private final List<T> repository;

    public Repository() {
        repository = new ArrayList<>();
    };

    public T find(Predicate<T> predicate) {
        for (T  item : repository) {
            if(predicate.test(item)) {
                return item;
            }
        }
        return null;
    }

    public void add(T item) {
        repository.add(item);
    }

    public boolean remove(T item) {
        return repository.remove(item);
    }
}
