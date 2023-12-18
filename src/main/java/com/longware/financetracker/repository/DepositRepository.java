package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface DepositRepository extends EntityRepositoryInterface<Deposit, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public Optional<Deposit> findByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(Deposit deposit) {
        return existsByDescriptionAndUserAccount(deposit.getDescription(), deposit.getUserAccount());
    }

    public default Optional<Deposit> getEntity(Deposit deposit) {
        return findByDescriptionAndUserAccount(deposit.getDescription(), deposit.getUserAccount());
    }

}
