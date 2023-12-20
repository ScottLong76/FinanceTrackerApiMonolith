package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.BankTransactionRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Service Layer for BankTransaction Entity
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class BankTransactionService {

    private final BankTransactionRepository bankTransactionRepository;

    // Write methods to interact with all methods currently defined in the
    // BankTransactionRepository
    public void deleteAll() {
        bankTransactionRepository.deleteAll();
    }

    public void deleteById(Long id) {
        bankTransactionRepository.deleteById(id);
    }

    public void delete(BankTransaction bankTransaction) {
        bankTransactionRepository.delete(bankTransaction);
    }

    public void saveAll(Iterable<BankTransaction> bankTransactions) {
        bankTransactionRepository.saveAll(bankTransactions);
    }

    public BankTransaction save(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
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

    public Iterable<BankTransaction> findByUserAccount(UserAccount userAccount) {
        return bankTransactionRepository.findByUserAccount(userAccount);
    }

    public long count() {
        return bankTransactionRepository.count();
    }

    public boolean entityExists(BankTransaction bankTransaction) {
        return bankTransactionRepository.entityExists(bankTransaction);
    }

    public Optional<BankTransaction> getEntity(BankTransaction bankTransaction) {
        return bankTransactionRepository.getEntity(bankTransaction);
    }

}
