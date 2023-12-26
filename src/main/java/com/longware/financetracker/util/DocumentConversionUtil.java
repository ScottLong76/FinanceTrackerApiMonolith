package com.longware.financetracker.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
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

    public static final String[] columnNames = { "DATE", "DESCRIPTION", "BANK", "AMOUNT", "EXPENSE", "DEPOSIT",
            "VENDOR",
            "DEPOSIT CATEGORY", "EXPENSE CATEGORY" };

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
    public static List<BankTransaction> convertWorkbookToBankTransactions(Workbook workbook) {
        List<BankTransaction> bankTransactions = new ArrayList<>();

        Sheet sheet = workbook.getSheetAt(0); // Assuming sheet 0 contains the bank transactions

        Row headerRow = sheet.getRow(0);

        // Iterate through the rows and create BankTransaction objects
        Iterator<Row> rowIterator = sheet.iterator();
        rowIterator.next(); // Skip the header row
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            BankTransaction bankTransaction = new BankTransaction();
            Vendor vendor = new Vendor();
            Expense expense = new Expense();
            Deposit deposit = new Deposit();

            for (int i = 0; i < headerRow.getLastCellNum(); i++) {
                Cell cell = row.getCell(i);
                if (cell != null) {
                    String columnHeader = headerRow.getCell(i).getStringCellValue().toUpperCase();
                    switch (columnHeader) {
                        case "DATE":
                            Date date = cell.getDateCellValue();
                            LocalDate transactionDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                            bankTransaction.setTransactionDate(transactionDate);
                            break;
                        case "DESCRIPTION":
                            bankTransaction.setDescription(cell.getStringCellValue());
                            break;
                        case "BANK":
                            bankTransaction.setBank(Bank.builder().description(cell.getStringCellValue()).build());
                            break;
                        case "AMOUNT":
                            bankTransaction.setAmount(BigDecimal.valueOf(cell.getNumericCellValue()));
                            break;
                        case "DEPOSIT AMOUNT":
                            bankTransaction.setAmount(BigDecimal.valueOf(cell.getNumericCellValue()));
                            break;
                        case "WITHDRAWAL AMOUNT":
                            bankTransaction.setAmount(BigDecimal.valueOf(cell.getNumericCellValue()));
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
                        case "MERCHANT":
                            vendor.setDescription(cell.getStringCellValue());
                            bankTransaction.setVendor(vendor);
                            break;
                        case "DEPOSIT CATEGORY":
                            DepositCategory depositCategory = DepositCategory.builder()
                                    .description(cell.getStringCellValue()).build();
                            deposit.setDepositCategory(depositCategory);
                            break;
                        case "EXPENSE CATEGORY":
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

    public static List<BankTransaction> convertFileToBankTransactions(File file) {
        Workbook workbook = convertDocumentToWorkBook(file);
        return convertWorkbookToBankTransactions(workbook);
    }

    public static File convertMultipartFileToFile(MultipartFile multipartFile) {
        File file = new File(multipartFile.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(multipartFile.getBytes());
        } catch (IOException ex) {
            log.log(Level.SEVERE, "IOException while converting multipart file to file", ex);
        }

        return file;
    }

    /**
     * Convert a List of BankTransactions into an XLSX file
     *
     * @param bankTransactions the List of BankTransactions to convert
     * @param filePath         the file path to save the XLSX file
     * @throws IOException if an I/O error occurs
     */
    public static File convertBankTransactionsToXlsx(Iterable<BankTransaction> bankTransactions, String filePath)
            throws IOException {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("Bank Transactions");

            // Create header row
            Row headerRow = sheet.createRow(0);
            for (int i = 0; i < columnNames.length; i++) {
                Cell cell = headerRow.createCell(i);
                cell.setCellValue(columnNames[i]);
            }

            // Create data rows
            int rowNum = 1;
            for (BankTransaction transaction : bankTransactions) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(transaction.getTransactionDate());
                row.createCell(1).setCellValue(transaction.getDescription());
                row.createCell(2)
                        .setCellValue(transaction.getBank() != null ? transaction.getBank().getDescription() : null);
                row.createCell(3).setCellValue(transaction.getAmount().doubleValue());
                row.createCell(4)
                        .setCellValue(transaction.getVendor() != null && transaction.getVendor().getExpense() != null
                                ? transaction.getVendor().getExpense().getDescription()
                                : null);
                row.createCell(5).setCellValue(
                        transaction.getDeposit() != null ? transaction.getDeposit().getDescription() : null);
                row.createCell(6).setCellValue(
                        transaction.getDeposit() != null && transaction.getDeposit().getDepositCategory() != null
                                ? transaction.getDeposit().getDepositCategory().getDescription()
                                : null);
                row.createCell(7).setCellValue(
                        transaction.getVendor() != null ? transaction.getVendor().getDescription() : null);
                row.createCell(8)
                        .setCellValue(transaction.getDeposit() != null
                                && transaction.getDeposit().getDepositCategory() != null
                                && transaction.getVendor() != null && transaction.getVendor().getExpense() != null
                                && transaction.getVendor().getExpense().getExpenseCategory() != null
                                        ? transaction.getVendor().getExpense().getExpenseCategory().getDescription()
                                        : null);
            }

            // Write the workbook to the file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }
        }

        return new File(filePath);
    }

    /**
     * Convert a List of BankTransactions into a CSV file
     *
     * @param bankTransactions the List of BankTransactions to convert
     * @param filePath         the file path to save the CSV file
     * @throws IOException if an I/O error occurs
     */
    public static void convertBankTransactionsToCsv(List<BankTransaction> bankTransactions, String filePath)
            throws IOException {
        // Implement the logic to convert BankTransactions to CSV format and save it to
        // the file
    }

    /**
     * Convert a List of BankTransactions into a JSON file
     *
     * @param bankTransactions the List of BankTransactions to convert
     * @param filePath         the file path to save the JSON file
     * @throws IOException if an I/O error occurs
     */
    public static void convertBankTransactionsToJson(List<BankTransaction> bankTransactions, String filePath)
            throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File(filePath), bankTransactions);
    }
}
