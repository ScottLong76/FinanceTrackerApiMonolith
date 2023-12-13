package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.repository.ExpenseCategoryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the ExpenseCategory entity.
 */
@RestController
@RequestMapping("/expenseCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseCategoryController {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    // Write methods to create, update, and delete ExpenseCategory objects using
    // available methods in the ExpenseCategoryRepository interface.

    // Write a method to return an ExpenseCategory object by its id.
    @RequestMapping("/getExpenseCategoryById")
    public ExpenseCategory getExpenseCategoryById(Long id) {
        return expenseCategoryRepository.findById(id).orElse(null);
    }

    // Write a method to return all ExpenseCategory objects.
    @RequestMapping("/getAllExpenseCategories")
    public Iterable<ExpenseCategory> getAllExpenseCategories() {
        return expenseCategoryRepository.findAll();
    }

    @RequestMapping("/saveExpenseCategory")
    public ExpenseCategory saveExpenseCategory(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    @RequestMapping("/deleteExpenseCategory")
    public void deleteExpenseCategory(ExpenseCategory expenseCategory) {
        expenseCategoryRepository.delete(expenseCategory);
    }

}
