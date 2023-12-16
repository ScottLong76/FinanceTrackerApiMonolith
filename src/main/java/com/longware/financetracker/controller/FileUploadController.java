package com.longware.financetracker.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

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
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.service.BankTransactionService;
import com.longware.financetracker.service.DepositCategoryService;
import com.longware.financetracker.service.DepositService;
import com.longware.financetracker.service.ExpenseCategoryService;
import com.longware.financetracker.service.VendorService;
import com.longware.financetracker.util.DocumentConversionUtil;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RestController
@RequestMapping("/fileUpload")
@RequiredArgsConstructor
@Getter
@Setter
public class FileUploadController {

    private final BankTransactionService bankTransactionService;

    private final ExpenseCategoryService expenseCategoryService;

    private final DepositCategoryService depositCategoryService;

    private final VendorService vendorService;

    private final DepositService depositService;

    @PostMapping("/upload")
    public void uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        List<BankTransaction> bankTransactions = transformFileToBankTransactions(file);
        saveBankTransactions(bankTransactions);
    }

    private List<BankTransaction> transformFileToBankTransactions(MultipartFile file) throws IOException {

        File convertedFile = DocumentConversionUtil.convertMultipartFileToFile(file);
        return DocumentConversionUtil.convertFileToBankTransactions(convertedFile);
    }

    private void saveBankTransactions(List<BankTransaction> bankTransactions) {

        for (BankTransaction bankTransaction : bankTransactions) {
            Vendor vendor = bankTransaction.getVendor();
            Expense expense = vendor.getExpense();
            ExpenseCategory expenseCategory = expense.getExpenseCategory();
            Deposit deposit = bankTransaction.getDeposit();
            DepositCategory depositCategory = deposit.getDepositCategory();

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

        }
    }
}
