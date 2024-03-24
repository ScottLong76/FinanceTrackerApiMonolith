package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ExpenseCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.ExpenseCategoryService;
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
 * This class is the REST controller for the ExpenseCategory entity.
 */
@RestController
@RequestMapping("/expenseCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class ExpenseCategoryController {

    private final ExpenseCategoryService expenseCategoryService;
    private final UserAccountUtil userAccountUtil;

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
        return expenseCategoryService.findById(id).orElse(null);
    }

    // Write a method to return all ExpenseCategory objects.
    @Operation(summary = "Get all ExpenseCategories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ExpenseCategory.class)) }) })
    @RequestMapping("/getAllExpenseCategories")
    public Page<ExpenseCategory> getAllExpenseCategories(Principal principal,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return expenseCategoryService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save ExpenseCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ExpenseCategory.class)) }) })
    @RequestMapping("/saveExpenseCategory")
    public ExpenseCategory saveExpenseCategory(ExpenseCategory expenseCategory, Principal principal) {
        return expenseCategoryService.save(expenseCategory);
    }

    @Operation(summary = "Delete ExpenseCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation") })
    @RequestMapping("/deleteExpenseCategory")
    public void deleteExpenseCategory(ExpenseCategory expenseCategory, Principal principal) {
        expenseCategoryService.delete(expenseCategory);
    }

}
