package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.BudgetEntry;
import com.longware.financetracker.repository.BudgetEntryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@Getter
@Setter
@RequiredArgsConstructor
public class BudgetEntryService {

    public final BudgetEntryRepository budgetEntryRepository;

    // Write methods to interact with all methods currently defined in the
    // BudgetEntryRepository

    public void deleteAll() {
        budgetEntryRepository.deleteAll();
    }

    public void deleteById(Long id) {
        budgetEntryRepository.deleteById(id);
    }

    public void saveAll(Iterable<BudgetEntry> budgetEntries) {
        budgetEntryRepository.saveAll(budgetEntries);
    }

    public void save(BudgetEntry budgetEntry) {
        budgetEntryRepository.save(budgetEntry);
    }

    public Optional<BudgetEntry> findById(Long id) {
        return budgetEntryRepository.findById(id);
    }

}
