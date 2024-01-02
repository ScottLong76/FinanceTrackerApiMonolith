package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BudgetedTransaction;
import com.longware.financetracker.repository.BudgetedTransactionRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the BudgetedTransaction entity.
 */
@RestController
@RequestMapping("/budgetedTransaction")
@RequiredArgsConstructor
@Getter
@Setter
public class BudgetedTransactionController {

    private final BudgetedTransactionRepository budgetedTransactionRepository;

    // Write methods to create, update, and delete BudgetedTransaction objects using
    // available methods in the BudgetedTransactionRepository interface.

    // Write a method to return a BudgetedTransaction object by its id.
    @RequestMapping("/getBudgetedTransactionById")
    public BudgetedTransaction getBudgetedTransactionById(Long id, Principal principal) {
        return budgetedTransactionRepository.findById(id).orElse(null);
    }

    // Write a method to return all BudgetedTransaction objects.
    @RequestMapping("/getAllBudgetedTransactions")
    public Iterable<BudgetedTransaction> getAllBudgetedTransactions(Principal principal) {
        return budgetedTransactionRepository.findAll();
    }

    @RequestMapping("/saveBudgetedTransaction")
    public BudgetedTransaction saveBudgetedTransaction(BudgetedTransaction budgetedTransaction, Principal principal) {
        return budgetedTransactionRepository.save(budgetedTransaction);
    }

    @RequestMapping("/deleteBudgetedTransaction")
    public void deleteBudgetedTransaction(BudgetedTransaction budgetedTransaction, Principal principal) {
        budgetedTransactionRepository.delete(budgetedTransaction);
    }

}
