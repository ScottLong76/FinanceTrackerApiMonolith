package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Expense;
import com.longware.financetracker.repository.ExpenseRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the Expense entity.
 */
@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseController {

    private final ExpenseRepository expenseRepository;

    // Write methods to create, update, and delete Expense objects using available
    // methods in the ExpenseRepository interface.

    // Write a method to return an Expense object by its id.
    @RequestMapping("/getExpenseById")
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElse(null);
    }

    // Write a method to return all Expense objects.
    @RequestMapping("/getAllExpenses")
    public Iterable<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    @RequestMapping("/saveExpense")
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @RequestMapping("/deleteExpense")
    public void deleteExpense(Expense expense) {
        expenseRepository.delete(expense);
    }

}
