package com.longware.financetracker.repository.interfaces;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.longware.financetracker.entities.UserAccount;

@NoRepositoryBean
public interface EntityRepositoryInterface<T, ID> extends JpaRepository<T, ID> {

    public boolean entityExists(T entity);

    public Optional<T> getEntity(T entity);

    public Page<T> findPageByUserAccount(UserAccount userAccount, Pageable pageRequest);

}
