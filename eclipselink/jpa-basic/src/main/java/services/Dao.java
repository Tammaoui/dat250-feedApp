package services;

public interface Dao<E, K> {

    void persist(E entity);

    void remove(E entity);

    E findById(K id);
}


