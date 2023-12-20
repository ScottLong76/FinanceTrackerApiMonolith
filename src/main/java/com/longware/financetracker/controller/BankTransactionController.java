package com.longware.financetracker.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.Optional;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.BankTransactionService;
import com.longware.financetracker.service.UserAccountService;
import com.longware.financetracker.util.DocumentConversionUtil;

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

    private final BankTransactionService bankTransactionService;

    private final UserAccountService userAccountService;

    // Implement methods to create, update, and delete BankTransaction objects using
    // available methods in the BankTransactionRepository interface.

    // Implement a method to return a BankTransaction object by its id.
    @RequestMapping("/getBankTransactionById")
    public BankTransaction getBankTransactionById(Long id) {
        return bankTransactionService.findById(id).orElse(null);
    }

    // Implement a method to return all BankTransaction objects.
    @RequestMapping("/getAllBankTransactions")
    public Iterable<BankTransaction> getAllBankTransactions() {
        return bankTransactionService.findAll();
    }

    @RequestMapping("/saveBankTransaction")
    public BankTransaction saveBankTransaction(BankTransaction bankTransaction) {
        return bankTransactionService.save(bankTransaction);
    }

    @RequestMapping("/deleteBankTransaction")
    public void deleteBankTransaction(BankTransaction bankTransaction) {
        bankTransactionService.delete(bankTransaction);
    }

    @RequestMapping("/exportBankTransactions")
    public File exportBankTransactions(Principal principal) {

        File file = null;
        try {
            String username = principal.getName(); // Get the username of the authenticated user
            UserAccount userAccount = UserAccount.builder().userName(username).build();

            Optional<UserAccount> userAccountOptional = userAccountService.getEntity(userAccount);

            if (userAccountOptional.isEmpty()) {
                userAccount = userAccountService.save(userAccount);
            } else {
                userAccount = userAccountOptional.get();
            }
            Iterable<BankTransaction> bankTransactions = bankTransactionService
                    .findByUserAccount(userAccount);
            file = DocumentConversionUtil.convertBankTransactionsToXlsx(bankTransactions, "BankTransactions.xlsx");
            return file;
        } catch (IOException e) {
            // Handle the exception
            e.printStackTrace();
            return null; // Or handle the exception in an appropriate way
        }

    }
}