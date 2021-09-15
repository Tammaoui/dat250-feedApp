package services;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.lang.reflect.ParameterizedType;

public abstract class JpaDao<E, K> implements Dao<E, K> {

    private static final String PERSISTENCE_UNIT_NAME = "polls";

    private static EntityManagerFactory factory;

    public Class<E> entityClass;


    @PersistenceContext
    protected EntityManager entityManager;

    public JpaDao() {
        // Get class of the generic type E.
        this.entityClass = ((Class<E>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0]);

        factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        entityManager = factory.createEntityManager();

    }

    public void persist(E entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    public void remove(E entity) {
        entityManager.getTransaction().begin();
        entityManager.remove(entity);
        entityManager.flush();
        entityManager.getTransaction().commit();
    }

    public E find(K id) {
        entityManager.getTransaction().begin();
        E result = entityManager.find(entityClass, id);
        entityManager.getTransaction().commit();
        return result;
    }

}
