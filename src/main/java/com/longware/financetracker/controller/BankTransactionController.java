package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.repository.BankTransactionRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the BankTransaction entity.
 */
@RestController
@RequestMapping("/bankTransaction")
@RequiredArgsConstructor
@Getter
@Setter
public class BankTransactionController {

    private final BankTransactionRepository bankTransactionRepository;

    // Implement methods to create, update, and delete BankTransaction objects using
    // available methods in the BankTransactionRepository interface.

    // Implement a method to return a BankTransaction object by its id.
    @RequestMapping("/getBankTransactionById")
    public BankTransaction getBankTransactionById(Long id) {
        return bankTransactionRepository.findById(id).orElse(null);
    }

    // Implement a method to return all BankTransaction objects.
    @RequestMapping("/getAllBankTransactions")
    public Iterable<BankTransaction> getAllBankTransactions() {
        return bankTransactionRepository.findAll();
    }

    @RequestMapping("/saveBankTransaction")
    public BankTransaction saveBankTransaction(BankTransaction bankTransaction) {
        return bankTransactionRepository.save(bankTransaction);
    }

    @RequestMapping("/deleteBankTransaction")
    public void deleteBankTransaction(BankTransaction bankTransaction) {
        bankTransactionRepository.delete(bankTransaction);
    }

}
