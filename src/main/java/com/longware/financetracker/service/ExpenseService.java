package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Expense;
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

    public void save(Expense expense) {
        expenseRepository.save(expense);
    }

    public Optional<Expense> findById(Long id) {
        return expenseRepository.findById(id);
    }

}
