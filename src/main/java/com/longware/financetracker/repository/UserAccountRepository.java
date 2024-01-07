package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface UserAccountRepository extends EntityRepositoryInterface<UserAccount, Long> {

    public Iterable<UserAccount> findAllByUserName(String username);

    public boolean existsByUserName(String username);

    public default boolean entityExists(UserAccount userAccount) {
        return existsByUserName(userAccount.getUserName());
    }

    public Optional<UserAccount> findByUserName(String username);

    public default Optional<UserAccount> getEntity(UserAccount userAccount) {
        return findByUserName(userAccount.getUserName());
    }

}
