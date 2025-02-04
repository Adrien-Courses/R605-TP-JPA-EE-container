package fr.adriencaubel.generic;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Stateless
public class GenericDao<T extends  BaseEntity> {

    @PersistenceContext
    private EntityManager em;

    private Class<T> entityClass;

    public GenericDao() {}

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T create(final T t) {
        return null;
    }

    public void delete(final Object id) {
        return;
    }

    public T find(final Object id) {
        return em.find(entityClass ,id);
    }

    public T update(final T t) {
        return null;
    }
}