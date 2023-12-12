package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.repository.ExpenseCategoryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Service class for the ExpenseCategory entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    // Write methods to interact with all methods currently defined in the
    // ExpenseCategoryRepository

    public void deleteAll() {
        expenseCategoryRepository.deleteAll();
    }

    public void deleteById(Long id) {
        expenseCategoryRepository.deleteById(id);
    }

    public void saveAll(Iterable<ExpenseCategory> expenseCategories) {
        expenseCategoryRepository.saveAll(expenseCategories);
    }

    public void save(ExpenseCategory expenseCategory) {
        expenseCategoryRepository.save(expenseCategory);
    }

    public Optional<ExpenseCategory> findById(Long id) {
        return expenseCategoryRepository.findById(id);
    }

    public Iterable<ExpenseCategory> findAll() {
        return expenseCategoryRepository.findAll();
    }

}
