package com.longware.financetracker.service.interfaces;

import java.util.ArrayList;
import java.util.List;


public interface EntityInterface<T> {

    public boolean entityExists(T entity);

    public default List<T> findNonExistingEntities(List<T> entities) {
        // Implement the logic to find non-existing entities
        List<T> nonExistingEntities = new ArrayList<>();
        for (T entity : entities) {
            if (!entityExists(entity)) {
                nonExistingEntities.add(entity);
            }
        }
        return nonExistingEntities;
    }

}
