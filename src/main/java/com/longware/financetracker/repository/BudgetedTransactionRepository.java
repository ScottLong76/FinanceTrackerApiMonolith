package com.longware.financetracker.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BudgetedTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BudgetedTransactionRepository extends EntityRepositoryInterface<BudgetedTransaction, Long> {

    public boolean existsByPaidAndTransactionAmountAndDueDateAndUserAccount(boolean paid, Float transactionAmount,
            Date dueDate, UserAccount userAccount);

    public Optional<BudgetedTransaction> findByPaidAndTransactionAmountAndDueDateAndUserAccount(boolean paid,
            Float transactionAmount, Date dueDate, UserAccount userAccount);

    public default boolean entityExists(BudgetedTransaction budgetedTransaction) {
        return existsByPaidAndTransactionAmountAndDueDateAndUserAccount(budgetedTransaction.isPaid(),
                budgetedTransaction.getTransactionAmount(), budgetedTransaction.getDueDate(),
                budgetedTransaction.getUserAccount());
    }

    public default Optional<BudgetedTransaction> getEntity(BudgetedTransaction budgetedTransaction) {
        return findByPaidAndTransactionAmountAndDueDateAndUserAccount(budgetedTransaction.isPaid(),
                budgetedTransaction.getTransactionAmount(), budgetedTransaction.getDueDate(),
                budgetedTransaction.getUserAccount());
    }
}
