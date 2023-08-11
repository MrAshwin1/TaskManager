package com.DOA;

import java.util.List;

public interface EntityAccessor<T> {
    void addEntity(T entity);
    void updateEntity(T entity);
    void deleteEntity(int id);
    T getEntityById(int id);
    List<T> getAllEntities();
}
