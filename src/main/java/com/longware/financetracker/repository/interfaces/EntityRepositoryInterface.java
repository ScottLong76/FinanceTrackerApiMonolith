package com.longware.financetracker.repository.interfaces;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepositoryInterface<T, ID> extends JpaRepository<T, ID> {

    public boolean entityExists(T entity);

}
