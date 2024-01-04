package com.longware.financetracker.service;

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

    public void saveAll(Iterable<BudgetedTransaction> budgetedTransactions) {
        budgetedTransactionRepository.saveAll(budgetedTransactions);
    }

    public BudgetedTransaction save(BudgetedTransaction budgetedTransaction) {
        return budgetedTransactionRepository.save(budgetedTransaction);
    }

    public Object findById(Long id) {
        return budgetedTransactionRepository.findById(id);
    }

    public Iterable<BudgetedTransaction> findAll() {
        return budgetedTransactionRepository.findAll();
    }

    public Iterable<BudgetedTransaction> findAllByUserAccount(UserAccount userAccount) {
        return budgetedTransactionRepository.findAllByUserAccount(userAccount);
    }

}
