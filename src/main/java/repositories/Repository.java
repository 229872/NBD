package repositories;

import jakarta.persistence.*;

import java.util.List;

public class Repository<T> {

    private final EntityManager em;
    private final Class<T> clazz;

    public Repository(Class<T> clazz, EntityManager em) {
        this.clazz = clazz;
        this.em = em;
    }
    public EntityManager getEm() {
        return em;
    }
    public T find(long id) {
        return em.find(clazz, id);
    }

    public List<T> find(Query query) {
        return query.getResultList();
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
