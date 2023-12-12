package com.longware.financetracker.service;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.BudgetedTransaction;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Setter
@Getter
public class BudgetedTransactionService {

    private final BudgetedTransactionService budgetedTransactionService;

    // Write methods to interact with all methods currently defined in the
    // BudgetedTransactionRepository

    public void deleteAll() {
        budgetedTransactionService.deleteAll();
    }

    public void deleteById(Long id) {
        budgetedTransactionService.deleteById(id);
    }

    public void saveAll(Iterable<BudgetedTransaction> budgetedTransactions) {
        budgetedTransactionService.saveAll(budgetedTransactions);
    }

    public void save(BudgetedTransaction budgetedTransaction) {
        budgetedTransactionService.save(budgetedTransaction);
    }

    public Object findById(Long id) {
        return budgetedTransactionService.findById(id);
    }

    public Iterable<BudgetedTransaction> findAll() {
        return budgetedTransactionService.findAll();
    }

}
