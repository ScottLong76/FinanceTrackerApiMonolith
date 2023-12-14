package com.longware.financetracker.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.entities.Expense;
import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.entities.Vendor;

import lombok.extern.java.Log;

/**
 * Utility class to convert uploaded documents of types XLSX, CSV, and JSON to
 * Java objects which can be serialized to the database
 */
@Log
public class DocumentConversionUtil {

    /**
     * Convert an uploaded XLSX, CSV, or JSON document into a WorkBook object
     * 
     * 
     */
    public static Workbook convertDocumentToWorkBook(File file) {
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(file);
        } catch (IOException ex) {
            log.log(Level.SEVERE, "IOException while converting document to workbook", ex);
        }

        return workbook;
    }

    /**
     * Convert a Workbook object into a List of BankTransaction objects based on the
     * specified column names
     *
     * @param workbook    the Workbook object to convert
     * @param columnNames the column names to extract from the sheet
     * @return a List of BankTransaction objects
     */
    public static List<BankTransaction> convertWorkbookToBankTransactions(Workbook workbook, String[] columnNames) {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0); // Assuming sheet 0 contains the bank transactions

        // Find the column indexes based on the column names
        int[] columnIndexes = new int[columnNames.length];
        Row headerRow = sheet.getRow(0);
        for (int i = 0; i < columnNames.length; i++) {
            String columnName = columnNames[i];
            for (int j = 0; j < headerRow.getLastCellNum(); j++) {
                Cell cell = headerRow.getCell(j);
                if (cell != null && cell.getCellType() == CellType.STRING
                        && columnName.equals(cell.getStringCellValue())) {
                    columnIndexes[i] = j;
                    break;
                }
            }
        }

        // Iterate through the rows and create BankTransaction objects
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip the header row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            BankTransaction bankTransaction = new BankTransaction();
            Vendor vendor = new Vendor();
            Expense expense = new Expense();
            Deposit deposit = new Deposit();

            for (int i = 0; i < columnIndexes.length; i++) {
                int columnIndex = columnIndexes[i];
                Cell cell = row.getCell(columnIndex);
                if (cell != null) {
                    switch (columnNames[i]) {
                        case "DATE":
                            bankTransaction.setTransactionDate(cell.getDateCellValue());
                            break;
                        case "DESCRIPTION":
                            bankTransaction.setDescription(cell.getStringCellValue());
                            break;
                        case "BANK":
                            bankTransaction.setBank(Bank.builder().description(cell.getStringCellValue()).build());
                            break;
                        case "AMOUNT":
                            bankTransaction.setAmount((float) cell.getNumericCellValue());
                            break;
                        case "DEPOSIT AMOUNT":
                            bankTransaction.setAmount((float) cell.getNumericCellValue());
                            break;
                        case "WITHDRAWAL AMOUNT":
                            bankTransaction.setAmount((float) cell.getNumericCellValue());
                            break;
                        case "EXPENSE":
                            expense.setDescription(cell.getStringCellValue());
                            vendor.setExpense(expense);
                            break;
                        case "DEPOSIT":
                            deposit.setDescription(cell.getStringCellValue());
                            bankTransaction.setDeposit(deposit);
                            break;
                        case "INCOME":
                            deposit.setDescription(cell.getStringCellValue());
                            bankTransaction.setDeposit(deposit);
                            break;
                        case "VENDOR":
                            vendor.setDescription(cell.getStringCellValue());
                            bankTransaction.setVendor(vendor);
                            break;
                        case "DEPOSIT CATEGORY":
                            DepositCategory depositCategory = DepositCategory.builder()
                                    .description(cell.getStringCellValue()).build();
                            deposit.setDepositCategory(depositCategory);
                            break;
                        case "EXPENSE_CATEGORY":
                            expense.setExpenseCategory(ExpenseCategory.builder().description(cell.getStringCellValue())
                                    .userAccount(bankTransaction.getUserAccount()).build());
                            break;

                    }
                }
            }

            bankTransactions.add(bankTransaction);
        }

        return bankTransactions;
    }
}
