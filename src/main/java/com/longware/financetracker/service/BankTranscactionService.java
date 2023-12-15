package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.repository.BankTransactionRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Service Layer for BankTransaction Entity
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class BankTranscactionService {

    private final BankTransactionRepository bankTransactionRepository;

    // Write methods to interact with all methods currently defined in the
    // BankTransactionRepository
    public void deleteAll() {
        bankTransactionRepository.deleteAll();
    }

    public void deleteById(Long id) {
        bankTransactionRepository.deleteById(id);
    }

    public void saveAll(Iterable<BankTransaction> bankTransactions) {
        bankTransactionRepository.saveAll(bankTransactions);
    }

    public void save(BankTransaction bankTransaction) {
        bankTransactionRepository.save(bankTransaction);
    }

    public Optional<BankTransaction> findById(Long id) {
        return bankTransactionRepository.findById(id);
    }

    public Iterable<BankTransaction> findAll() {
        return bankTransactionRepository.findAll();
    }

    public Iterable<BankTransaction> findAllById(Iterable<Long> ids) {
        return bankTransactionRepository.findAllById(ids);
    }

    public long count() {
        return bankTransactionRepository.count();
    }

    public boolean exists(BankTransaction bankTransaction) {
        return bankTransactionRepository.existsByDescriptionAndAmountAndTransactionDateAndUserAccount(
                bankTransaction.getDescription(), bankTransaction.getAmount(), bankTransaction.getTransactionDate(),
                bankTransaction.getUserAccount());
    }
}
