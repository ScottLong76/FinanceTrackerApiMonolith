package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BudgetEntry;
import com.longware.financetracker.repository.BudgetEntryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the BudgetEntry entity.
 */
@RestController
@RequestMapping("/budgetEntry")
@RequiredArgsConstructor
@Getter
@Setter
public class BudgetEntryController {

    private final BudgetEntryRepository budgetEntryRepository;

    // Write methods to create, update, and delete BudgetEntry objects using
    // available methods in the BudgetEntryRepository interface.

    // Write a method to return a BudgetEntry object by its id.
    @RequestMapping("/getBudgetEntryById")
    public BudgetEntry getBudgetEntryById(Long id) {
        return budgetEntryRepository.findById(id).orElse(null);
    }

    // Write a method to return all BudgetEntry objects.
    @RequestMapping("/getAllBudgetEntries")
    public Iterable<BudgetEntry> getAllBudgetEntries() {
        return budgetEntryRepository.findAll();
    }

    @RequestMapping("/saveBudgetEntry")
    public BudgetEntry saveBudgetEntry(BudgetEntry budgetEntry) {
        return budgetEntryRepository.save(budgetEntry);
    }

    @RequestMapping("/deleteBudgetEntry")
    public void deleteBudgetEntry(BudgetEntry budgetEntry) {
        budgetEntryRepository.delete(budgetEntry);
    }

}
