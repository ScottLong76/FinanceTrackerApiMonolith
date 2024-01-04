package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.BankRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Service Layer for Bank Entity
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class BankService {

    private final BankRepository bankRepository;

    // Write methods to interact with all methods currently defined in the
    // BankRepository

    public void deleteAll() {
        bankRepository.deleteAll();
    }

    public void deleteById(Long id) {
        bankRepository.deleteById(id);
    }

    public void saveAll(Iterable<Bank> banks) {
        bankRepository.saveAll(banks);
    }

    public Bank save(Bank bank) {
        return bankRepository.save(bank);
    }

    public Optional<Bank> findById(Long id) {
        return bankRepository.findById(id);
    }

    public Iterable<Bank> findAll() {
        return bankRepository.findAll();
    }

    public Iterable<Bank> findAllById(Iterable<Long> ids) {
        return bankRepository.findAllById(ids);
    }

    public long count() {
        return bankRepository.count();
    }

    public boolean existsById(Long id) {
        return bankRepository.existsById(id);
    }

    public boolean entityExists(Bank bank) {
        return bankRepository.entityExists(bank);
    }

    public Optional<Bank> getEntity(Bank bank) {
        return bankRepository.getEntity(bank);
    }

    public void delete(Bank bank) {
        bankRepository.delete(bank);
    }

    public Iterable<Bank> findAllByUserAccount(UserAccount userAccount) {
        return bankRepository.findAllByUserAccount(userAccount);
    }
}
