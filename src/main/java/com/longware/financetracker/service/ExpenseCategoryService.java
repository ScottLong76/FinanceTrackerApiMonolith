package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.entities.UserAccount;
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

    public ExpenseCategory save(ExpenseCategory expenseCategory) {
        return (expenseCategory != null ? expenseCategoryRepository.save(expenseCategory) : null);
    }

    public Optional<ExpenseCategory> findById(Long id) {
        return expenseCategoryRepository.findById(id);
    }

    public Iterable<ExpenseCategory> findAll() {
        return expenseCategoryRepository.findAll();
    }

    public boolean entityExists(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.entityExists(expenseCategory);
    }

    public Optional<ExpenseCategory> getEntity(ExpenseCategory expenseCategory) {
        return expenseCategoryRepository.getEntity(expenseCategory);
    }

    public Iterable<ExpenseCategory> findAllByUserAccount(UserAccount userAccount) {
        return expenseCategoryRepository.findAllByUserAccount(userAccount);
    }

}
