package com.longware.financetracker.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BudgetedTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BudgetedTransactionRepository extends EntityRepositoryInterface<BudgetedTransaction, Long> {

    public boolean existsByPaidAndTransactionAmountAndDueDaateAndUserAccount(boolean paid, Float transactionAmount,
            Date dueDate, UserAccount userAccount);

    public default boolean entityExists(BudgetedTransaction budgetedTransaction) {
        return existsByPaidAndTransactionAmountAndDueDaateAndUserAccount(budgetedTransaction.isPaid(),
                budgetedTransaction.getTransactionAmount(), budgetedTransaction.getDueDate(),
                budgetedTransaction.getUserAccount());
    }
}
