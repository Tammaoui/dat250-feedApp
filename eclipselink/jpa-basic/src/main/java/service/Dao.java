package service;

import java.util.ArrayList;

public interface Dao<E, K> {

    void persist(E entity);

    void remove(E entity);

    E find(K id);

    ArrayList<E> getAll();
}


