package com.longware.financetracker.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import com.longware.financetracker.util.UserAccountUtil;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.java.Log;

@RestController
@RequestMapping("/bankTransaction")
@RequiredArgsConstructor
@Getter
@Setter
@Log
@Tag(name = "Bank Transaction", description = "APIs for managing bank transactions")
public class BankTransactionController {

    private final BankTransactionService bankTransactionService;

    private final UserAccountService userAccountService;

    private final BankService bankService;

    private final ExpenseCategoryService expenseCategoryService;

    private final DepositCategoryService depositCategoryService;

    private final VendorService vendorService;

    private final DepositService depositService;

    private final ExpenseService expenseService;

    private final UserAccountUtil userAccountUtil;

    @Operation(summary = "Get a bank transaction by ID")
    @GetMapping("/getBankTransactionById")
    public BankTransaction getBankTransactionById(
            @Parameter(description = "ID of the bank transaction") @RequestParam Long id) {
        return bankTransactionService.findById(id).orElse(null);
    }

    @Operation(summary = "Get all bank transactions")
    @GetMapping("/getAllBankTransactions")
    public Iterable<BankTransaction> getAllBankTransactions(
            @AuthenticationPrincipal Principal principal) {
        return bankTransactionService.findAll();
    }

    @Operation(summary = "Save a bank transaction")
    @PostMapping("/saveBankTransaction")
    public BankTransaction saveBankTransaction(
            @RequestBody BankTransaction bankTransaction,
            @AuthenticationPrincipal Principal principal) {
        return bankTransactionService.save(bankTransaction);
    }

    @Operation(summary = "Delete a bank transaction")
    @DeleteMapping("/deleteBankTransaction")
    public void deleteBankTransaction(
            @RequestBody BankTransaction bankTransaction,
            @AuthenticationPrincipal Principal principal) {
        bankTransactionService.delete(bankTransaction);
    }

    @Operation(summary = "Export bank transactions to a file")
    @GetMapping("/exportBankTransactions")
    public File exportBankTransactions(
            @AuthenticationPrincipal Principal principal) {
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

    @Operation(summary = "Upload a file")
    @PostMapping("/upload")
    public void uploadFile(
            @Parameter(description = "File to upload") @RequestParam("file") MultipartFile file,
            @AuthenticationPrincipal Principal principal) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);

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

    @Operation(summary = "Upload OFX file")
    @PostMapping("/uploadOfx")
    public void uploadOfxFile(
            @Parameter(description = "OFX file to upload") @RequestParam("file") MultipartFile file,
            @Parameter(description = "Bank information") @RequestParam("bank") String bankStr,
            @AuthenticationPrincipal Principal principal) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        ObjectMapper objectMapper = new ObjectMapper();
        Bank bank = null;
        try {
            bank = objectMapper.readValue(bankStr, Bank.class);
        } catch (JsonProcessingException e) {
            // Handle the exception
            e.printStackTrace();
            return; // Or handle the exception in an appropriate way
        }
        final Bank finalBank = bank;
        finalBank.setUserAccount(userAccount);
        Optional<Bank> bankOptional = bankService.getEntity(finalBank);
        bank = bankOptional.orElseGet(() -> bankService.save(finalBank));

        bankTransactionService.processOFXFile(DocumentConversionUtil.convertMultipartFileToFile(file), bank,
                userAccount);
    }
}