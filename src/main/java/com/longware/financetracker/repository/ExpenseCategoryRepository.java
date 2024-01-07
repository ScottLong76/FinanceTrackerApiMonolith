package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ExpenseCategoryRepository extends EntityRepositoryInterface<ExpenseCategory, Long> {

    public Iterable<ExpenseCategory> findAllByUserAccount(UserAccount userAccount);

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public Optional<ExpenseCategory> findByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(ExpenseCategory expenseCategory) {
        if (expenseCategory == null) {
            return false;
        }
        return existsByDescriptionAndUserAccount(expenseCategory.getDescription(), expenseCategory.getUserAccount());
    }

    public default Optional<ExpenseCategory> getEntity(ExpenseCategory expenseCategory) {
        if (expenseCategory == null || expenseCategory.getDescription() == null
                || expenseCategory.getUserAccount() == null) {
            return Optional.empty();
        }
        return findByDescriptionAndUserAccount(expenseCategory.getDescription(), expenseCategory.getUserAccount());
    }

}
