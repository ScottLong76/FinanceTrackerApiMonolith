package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Expense;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.ExpenseService;
import com.longware.financetracker.util.UserAccountUtil;

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
 * This class is the REST controller for the Expense entity.
 */
@RestController
@RequestMapping("/expense")
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseController {

    private final ExpenseService expenseService;
    private final UserAccountUtil userAccountUtil;

    // Write methods to create, update, and delete Expense objects using available
    // methods in the ExpenseRepository interface.

    // Write a method to return an Expense object by its id.
    @Operation(summary = "Get Expense by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the Expense", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Expense.class)) }),
        @ApiResponse(responseCode = "404", description = "Expense not found") })
    @RequestMapping("/getExpenseById")
    public Expense getExpenseById(@Parameter(description = "Expense ID") Long id) {
        return expenseService.findById(id).orElse(null);
    }

    // Write a method to return all Expense objects.
    @Operation(summary = "Get all Expenses")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all Expenses", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Expense.class)) }) })
    @RequestMapping("/getAllExpenses")
    public Page<Expense> getAllExpenses(Principal principal,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return expenseService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save Expense")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Expense saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Expense.class)) }) })
    @RequestMapping("/saveExpense")
    public Expense saveExpense(Expense expense, Principal principal) {
        return expenseService.save(expense);
    }

    @Operation(summary = "Delete Expense")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Expense deleted") })
    @RequestMapping("/deleteExpense")
    public void deleteExpense(Expense expense, Principal principal) {
        expenseService.delete(expense);
    }

}
