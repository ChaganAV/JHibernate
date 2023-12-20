package models;

import java.util.Collection;

public interface Repository<T, Tid> {
    void add(T item);
    void update(T item);
    void delete(T item);
    T getById(Tid id);
    Collection<T> getAll();

    void add(Courses courses);
}
