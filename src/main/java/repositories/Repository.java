package repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Repository<T> {
    private static EntityManagerFactory factory =
            Persistence.createEntityManagerFactory("default");
    private static EntityManager em = factory.createEntityManager();
    private final Class<T> clazz;

    public Repository(Class<T> clazz) {
        this.clazz = clazz;
    }

    public T find(long id) {
        return em.find(clazz, id);
    }

    public void add(T item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.persist(item);
        transaction.commit();
    }

    public boolean remove(T item) {
        EntityTransaction transaction = em.getTransaction();
        transaction.begin();
        em.remove(item);
        if(transaction.isActive()) {
            transaction.commit();
            return true;
        } else {
            return false;
        }
    }
}
