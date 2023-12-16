package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ExpenseCategoryRepository extends EntityRepositoryInterface<ExpenseCategory, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(ExpenseCategory expenseCategory) {
        return existsByDescriptionAndUserAccount(expenseCategory.getDescription(), expenseCategory.getUserAccount());
    }

}
