package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

// Make me a Spring Data JPA repository for the Bank entity.
@Repository
public interface BankRepository extends EntityRepositoryInterface<Bank, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(Bank bank) {
        return existsByDescriptionAndUserAccount(bank.getDescription(), bank.getUserAccount());
    }
}
