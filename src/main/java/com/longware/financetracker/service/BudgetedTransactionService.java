package com.longware.financetracker.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.BudgetedTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.BudgetedTransactionRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class BudgetedTransactionService {

    private final BudgetedTransactionRepository budgetedTransactionRepository;

    // Write methods to interact with all methods currently defined in the
    // BudgetedTransactionRepository

    public void deleteAll() {
        budgetedTransactionRepository.deleteAll();
    }

    public void deleteById(Long id) {
        budgetedTransactionRepository.deleteById(id);
    }

    public void delete(BudgetedTransaction budgetedTransaction) {
        budgetedTransactionRepository.delete(budgetedTransaction);
    }

    public void saveAll(Iterable<BudgetedTransaction> budgetedTransactions) {
        budgetedTransactionRepository.saveAll(budgetedTransactions);
    }

    public BudgetedTransaction save(BudgetedTransaction budgetedTransaction) {
        return budgetedTransactionRepository.save(budgetedTransaction);
    }

    public Iterable<BudgetedTransaction> findAll() {
        return budgetedTransactionRepository.findAll();
    }

    public Page<BudgetedTransaction> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return budgetedTransactionRepository.findPageByUserAccount(userAccount, pageable);
    }

}
