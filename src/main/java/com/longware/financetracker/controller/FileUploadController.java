package com.longware.financetracker.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.service.BankService;
import com.longware.financetracker.service.BankTransactionService;
import com.longware.financetracker.service.DepositCategoryService;
import com.longware.financetracker.service.DepositService;
import com.longware.financetracker.service.ExpenseCategoryService;
import com.longware.financetracker.service.ExpenseService;
import com.longware.financetracker.service.UserAccountService;
import com.longware.financetracker.service.VendorService;
import com.longware.financetracker.util.DocumentConversionUtil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/fileUpload")
@RequiredArgsConstructor
@Getter
@Setter
@Log
public class FileUploadController {

    private final BankTransactionService bankTransactionService;

    private final ExpenseCategoryService expenseCategoryService;

    private final DepositCategoryService depositCategoryService;

    private final VendorService vendorService;

    private final DepositService depositService;

    private final UserAccountService userAccountService;

    private final ExpenseService expenseService;

    private final BankService bankService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file, Principal principal) {

        String username = principal.getName(); // Get the username of the authenticated user
        UserAccount userAccount = UserAccount.builder().userName(username).build();

        Optional<UserAccount> userAccountOptional = userAccountService.getEntity(userAccount);

        if (userAccountOptional.isEmpty()) {
            userAccount = userAccountService.save(userAccount);
        } else {
            userAccount = userAccountOptional.get();
        }

        try {
            List<BankTransaction> bankTransactions = transformFileToBankTransactions(file);
            saveBankTransactions(bankTransactions, userAccount);
        } catch (IOException e) {
            log.log(Level.WARNING, "Error occurred while uploading file", e);
            // Handle the error here
        }
    }

    private List<BankTransaction> transformFileToBankTransactions(MultipartFile file) throws IOException {
        File convertedFile = DocumentConversionUtil.convertMultipartFileToFile(file);
        return DocumentConversionUtil.convertFileToBankTransactions(convertedFile);
    }

    private void saveBankTransactions(List<BankTransaction> bankTransactions, UserAccount userAccount) {
        bankTransactions.forEach(bankTransaction -> {
            try {
                bankTransaction.setUserAccount(userAccount);
                Optional.ofNullable(bankTransaction.getBank())
                        .ifPresent(bank -> {
                            bank.setUserAccount(userAccount);
                            Optional<Bank> bankOptional = bankService.getEntity(bank);
                            bankTransaction.setBank(bankOptional.orElseGet(() -> bankService.save(bank)));
                        });
                Optional.ofNullable(bankTransaction.getVendor())
                        .ifPresent(vendor -> {
                            vendor.setUserAccount(userAccount);
                            Optional.ofNullable(vendor.getExpense())
                                    .ifPresent(expense -> {
                                        expense.setUserAccount(userAccount);
                                        expenseCategoryService.getEntity(expense.getExpenseCategory())
                                                .ifPresentOrElse(
                                                        expenseCategory -> expense.setExpenseCategory(expenseCategory),
                                                        () -> expense.setExpenseCategory(expenseCategoryService
                                                                .save(expense.getExpenseCategory())));
                                        expenseService.getEntity(expense)
                                                .ifPresentOrElse(
                                                        existingExpense -> vendor.setExpense(existingExpense),
                                                        () -> vendor.setExpense(expenseService.save(expense)));
                                    });
                            Optional<Vendor> vendorOptional = vendorService.getEntity(vendor);
                            bankTransaction.setVendor(vendorOptional.orElseGet(() -> vendorService.save(vendor)));
                        });
                Optional.ofNullable(bankTransaction.getDeposit())
                        .ifPresent(deposit -> {
                            deposit.setUserAccount(userAccount);
                            if (deposit.getDepositCategory() != null) {
                                deposit.getDepositCategory().setUserAccount(userAccount);
                            }
                            Optional<DepositCategory> depositCategoryOptional = depositCategoryService
                                    .getEntity(deposit.getDepositCategory());
                            deposit.setDepositCategory(depositCategoryOptional
                                    .orElseGet(() -> depositCategoryService.save(deposit.getDepositCategory())));
                            Optional<Deposit> depositOptional = depositService.getEntity(deposit);
                            bankTransaction.setDeposit(depositOptional.orElseGet(() -> depositService.save(deposit)));
                        });
                Optional<BankTransaction> existingBankTransactionOptional = bankTransactionService
                        .getEntity(bankTransaction);
                if (existingBankTransactionOptional.isPresent()) {
                    BankTransaction existingBankTransaction = existingBankTransactionOptional.get();
                    bankTransaction.setId(existingBankTransaction.getId());
                }
                bankTransactionService.save(bankTransaction);

            } catch (Exception e) {
                log.log(Level.SEVERE, "Error occurred while saving bank transaction", e);
                // Handle the error here
                return;
            }

        });
        log.log(Level.INFO, "{0} Bank transaction saved successfully", bankTransactions.size());
    }

    public List<BankTransaction> processOfxFile(MultipartFile file, Principal principal) {
        List<BankTransaction> bankTransactions = null;
        try {
            File convertedFile = DocumentConversionUtil.convertMultipartFileToFile(file);
            bankTransactions = DocumentConversionUtil.convertFileToBankTransactions(convertedFile);
            String username = principal.getName(); // Get the username of the authenticated user
            UserAccount userAccount = UserAccount.builder().userName(username).build();

            Optional<UserAccount> userAccountOptional = userAccountService.getEntity(userAccount);

            if (userAccountOptional.isEmpty()) {
                userAccount = userAccountService.save(userAccount);
            } else {
                userAccount = userAccountOptional.get();
            }
            saveBankTransactions(bankTransactions, userAccount);
        } catch (Exception e) {
            log.log(Level.WARNING, "Error occurred while uploading file", e);
            // Handle the error here
        }
        return bankTransactions;
    }
}
