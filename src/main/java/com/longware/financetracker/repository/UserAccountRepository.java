package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface UserAccountRepository extends EntityRepositoryInterface<UserAccount, Long> {

    public boolean existsByUserName(String username);

    public default boolean entityExists(UserAccount userAccount) {
        return existsByUserName(userAccount.getUserName());
    }

    public UserAccount findByUserName(String username);
}
