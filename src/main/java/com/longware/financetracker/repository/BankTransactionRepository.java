package com.longware.financetracker.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BankTransactionRepository extends EntityRepositoryInterface<BankTransaction, Long> {

        public boolean existsByDescriptionAndAmountAndTransactionDateAndUserAccount(String description,
                        BigDecimal transactionAmount, LocalDate transactionDate, UserAccount userAccount);

        public Optional<BankTransaction> findByDescriptionAndAmountAndTransactionDateAndUserAccount(String description,
                        BigDecimal transactionAmount, LocalDate transactionDate, UserAccount userAccount);

        public default boolean entityExists(BankTransaction bankTransaction) {
                return existsByDescriptionAndAmountAndTransactionDateAndUserAccount(bankTransaction.getDescription(),
                                bankTransaction.getAmount(), bankTransaction.getTransactionDate(),
                                bankTransaction.getUserAccount());
        }

        public default Optional<BankTransaction> getEntity(BankTransaction bankTransaction) {
                return findByDescriptionAndAmountAndTransactionDateAndUserAccount(bankTransaction.getDescription(),
                                bankTransaction.getAmount(), bankTransaction.getTransactionDate(),
                                bankTransaction.getUserAccount());
        }

}
