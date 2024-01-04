package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Expense;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.ExpenseRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Service class for the Expense entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    // Write methods to interact with all methods currently defined in the
    // ExpenseRepository

    public void deleteAll() {
        expenseRepository.deleteAll();
    }

    public void deleteById(Long id) {
        expenseRepository.deleteById(id);
    }

    public void saveAll(Iterable<Expense> expenses) {
        expenseRepository.saveAll(expenses);
    }

    public Expense save(Expense expense) {
        return (expense != null ? expenseRepository.save(expense) : null);
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

    public Iterable<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public boolean entityExists(Expense expense) {
        return expenseRepository.entityExists(expense);
    }

    public Optional<Expense> getEntity(Expense expense) {
        return expenseRepository.getEntity(expense);
    }

    public Iterable<Expense> findAllByUserAccount(UserAccount userAccount) {
        return expenseRepository.findAllByUserAccount(userAccount);
    }

}
