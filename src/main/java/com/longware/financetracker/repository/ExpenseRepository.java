package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Expense;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ExpenseRepository extends EntityRepositoryInterface<Expense, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(Expense expense) {
        return existsByDescriptionAndUserAccount(expense.getDescription(), expense.getUserAccount());
    }

}
