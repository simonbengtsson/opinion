package com.dat076hage.hage.persistence;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 * Base class for any persisting collection DAO = Data access object (also
 * called a Facade)
 *
 * NOTE:
 *
 * @param <T> Type
 * @param <K> Primary key (id)
 * @author hajo
 */
public abstract class AbstractDAO<T, K> implements IDAO<T, K> {

    private final Class<T> clazz;

    // To be overridden by subclasses
    protected abstract EntityManager getEntityManager();

    protected AbstractDAO(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void create(T t) {
        getEntityManager().persist(t);
    }

    @Override
    public void delete(K id) {
        T t = getEntityManager().getReference(clazz, id);
        getEntityManager().remove(t);
    }

    /**
     * Using out parameter
     *
     * @param t
     */
    @Override
    public void update(T t) {
        getEntityManager().merge(t);
    }

    @Override
    public T find(K id) {
        return getEntityManager().find(clazz, id);
    }

    @Override
    public int count() {
        EntityManager em = getEntityManager();
        // Warning because typename not found in string (clazz.getSimpleName())
        // Criteria API better, possible misstakes in String, NOTE space before t      
        Long n = em.createQuery("select count(t) from " + clazz.getSimpleName() + " t", Long.class)
                .getSingleResult();
        return n.intValue();
    }
}
