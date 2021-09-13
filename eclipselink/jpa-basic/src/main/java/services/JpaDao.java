package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class JpaDao<E, K> implements Dao<E, K> {

    private static final String PERSISTENCE_UNIT_NAME = "polls";
    private static EntityManagerFactory factory;

    protected Class<E> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao() {
        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();
    }

    public void persist(E entity) { entityManager.persist(entity); }

    public void remove(E entity) { entityManager.remove(entity); }

    public E findById(K id) { return entityManager.find(entityClass, id); }


}
