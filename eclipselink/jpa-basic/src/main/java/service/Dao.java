package service;

import java.util.List;

public interface Dao<E, K> {

    void persist(E entity);

    void remove(E entity);

    E find(K id);

    List<E> getAll();
}


