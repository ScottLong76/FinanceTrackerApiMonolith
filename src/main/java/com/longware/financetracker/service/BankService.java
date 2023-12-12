package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.repository.BankRepository;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Service Layer for Bank Entity
@Service
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BankService {

    private BankRepository bankRepository;

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

    public void save(Bank bank) {
        bankRepository.save(bank);
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

}
