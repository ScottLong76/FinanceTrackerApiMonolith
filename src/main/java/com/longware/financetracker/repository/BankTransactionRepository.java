package com.longware.financetracker.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

import jakarta.persistence.Entity;

@Repository
public interface BankTransactionRepository extends EntityRepositoryInterface<BankTransaction, Long>{

    public boolean existsByDescriptionAndAmountAndTransactionDateAndUserAccount(String description,
            double transactionAmount, Date transactionDate, UserAccount userAccount);

    public default boolean entityExists(BankTransaction bankTransaction) {
        return existsByDescriptionAndAmountAndTransactionDateAndUserAccount(bankTransaction.getDescription(),
                bankTransaction.getAmount(), bankTransaction.getTransactionDate(), bankTransaction.getUserAccount());
    }

}
