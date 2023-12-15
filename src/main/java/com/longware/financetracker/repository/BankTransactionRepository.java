package com.longware.financetracker.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.UserAccount;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

    public boolean existsByDescriptionAndAmountAndTransactionDateAndUserAccount(String description,
            double transactionAmount, Date transactionDate, UserAccount userAccount);

}
