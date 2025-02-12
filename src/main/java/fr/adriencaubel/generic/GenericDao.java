package fr.adriencaubel.generic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Stateless
public class GenericDao<T extends  BaseEntity> {

    @PersistenceContext
    protected EntityManager em;

    private Class<T> entityClass;

    public GenericDao() {}

    public GenericDao(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public T save(final T t) {
        em.persist(t);
        return t;
    }

    public T find(final Object id) {
        return em.find(entityClass ,id);
    }

    public List<T> findAll() {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }
    
    // Pagination
    public List<T> findAllPaginated(int page, int size) {
        return em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass)
                .setFirstResult(page * size)
                .setMaxResults(size)
                .getResultList();
    }
    
    // Pagination avec Jointure
    public List<T> findAllPaginatedAndJoin(int page, int size, List<String> fetchRelations) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(entityClass);
        Root<T> root = cq.from(entityClass);
     
        for (String relation : fetchRelations) {
            root.fetch(relation, JoinType.LEFT);
        }
     
        return em.createQuery(cq)
                .setFirstResult(page * size)  // ajout de la pagination
                .setMaxResults(size)
                .getResultList();  
    }

    // Méthode pour compter le nombre total d'entités
    public long count() {
        return em.createQuery("SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e", Long.class)
                .getSingleResult();
    }
    
    // Rechercher avec jointure
    public List<T> findByCriteria(Map<String, Object> criterias, List<String> fetchRelations) {
    	CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery<T> cq = cb.createQuery(entityClass);
    	Root<T> root = cq.from(entityClass);
    	
        for (String relation : fetchRelations) {
            root.fetch(relation, JoinType.LEFT);
        }
    	
    	List<Predicate> predicates = new ArrayList<Predicate>();
    	
    	for(Map.Entry<String, Object> entries : criterias.entrySet()) {
    		predicates.add(cb.equal(root.get(entries.getKey()), entries.getValue()));
    	}
    	
    	cq.where(cb.and(predicates.toArray(new Predicate[0])));
    	
        return em.createQuery(cq).getResultList();    
    }
    
}