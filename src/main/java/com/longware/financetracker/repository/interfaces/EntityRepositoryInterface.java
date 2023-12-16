package com.longware.financetracker.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntityRepositoryInterface<T, ID> extends JpaRepository<T, ID> {

    public boolean entityExists(T entity);

}
