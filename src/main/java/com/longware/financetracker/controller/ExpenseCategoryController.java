package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.repository.ExpenseCategoryRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the ExpenseCategory entity.
 */
@RestController
@RequestMapping("/expenseCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseCategoryController {

    private final ExpenseCategoryRepository expenseCategoryRepository;

    // Write methods to create, update, and delete ExpenseCategory objects using
    // available methods in the ExpenseCategoryRepository interface.

    // Write a method to return an ExpenseCategory object by its id.
    @Operation(summary = "Get ExpenseCategory by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ExpenseCategory.class)) }),
        @ApiResponse(responseCode = "404", description = "ExpenseCategory not found") })
    @RequestMapping("/getExpenseCategoryById")
    public ExpenseCategory getExpenseCategoryById(@Parameter(description = "ExpenseCategory id") Long id) {
        return expenseCategoryRepository.findById(id).orElse(null);
    }

    // Write a method to return all ExpenseCategory objects.
    @Operation(summary = "Get all ExpenseCategories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ExpenseCategory.class)) }) })
    @RequestMapping("/getAllExpenseCategories")
    public Iterable<ExpenseCategory> getAllExpenseCategories(Principal principal) {
        return expenseCategoryRepository.findAll();
    }

    @Operation(summary = "Save ExpenseCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ExpenseCategory.class)) }) })
    @RequestMapping("/saveExpenseCategory")
    public ExpenseCategory saveExpenseCategory(ExpenseCategory expenseCategory, Principal principal) {
        return expenseCategoryRepository.save(expenseCategory);
    }

    @Operation(summary = "Delete ExpenseCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation") })
    @RequestMapping("/deleteExpenseCategory")
    public void deleteExpenseCategory(ExpenseCategory expenseCategory, Principal principal) {
        expenseCategoryRepository.delete(expenseCategory);
    }

}
