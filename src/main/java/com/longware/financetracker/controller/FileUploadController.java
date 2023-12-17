package com.longware.financetracker.controller;

import java.io.File;
import java.io.IOException;
import java.security.Principal;
import java.util.List;
import java.util.logging.Level;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.entities.Expense;
import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.service.BankTransactionService;
import com.longware.financetracker.service.DepositCategoryService;
import com.longware.financetracker.service.DepositService;
import com.longware.financetracker.service.ExpenseCategoryService;
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

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file, Principal principal) {

        String username = principal.getName(); // Get the username of the authenticated user
        UserAccount userAccount = UserAccount.builder().userName(username).build();

        if (!userAccountService.entityExists(userAccount)) {
            userAccountService.save(userAccount);
        }

        userAccount = userAccountService.findByUserName(username);

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
        for (BankTransaction bankTransaction : bankTransactions) {
            bankTransaction.setUserAccount(userAccount);
            Vendor vendor = bankTransaction.getVendor();
            vendor.setUserAccount(userAccount);
            Expense expense = vendor.getExpense();
            expense.setUserAccount(userAccount);
            ExpenseCategory expenseCategory = expense.getExpenseCategory();
            expenseCategory.setUserAccount(userAccount);
            Deposit deposit = bankTransaction.getDeposit();
            deposit.setUserAccount(userAccount);
            DepositCategory depositCategory = deposit.getDepositCategory();
            depositCategory.setUserAccount(userAccount);

            try {
                if (!expenseCategoryService.entityExists(expenseCategory)) {
                    expenseCategoryService.save(expenseCategory);
                }

                if (!depositCategoryService.entityExists(depositCategory)) {
                    depositCategoryService.save(depositCategory);
                }

                if (!vendorService.entityExists(vendor)) {
                    vendorService.save(vendor);
                }

                if (!depositService.entityExists(deposit)) {
                    depositService.save(deposit);
                }

                if (!bankTransactionService.entityExists(bankTransaction)) {
                    bankTransactionService.save(bankTransaction);
                }
            } catch (Exception e) {
                log.log(Level.SEVERE, "Error occurred while saving bank transaction", e);
                // Handle the error here
            }
        }
    }
}
