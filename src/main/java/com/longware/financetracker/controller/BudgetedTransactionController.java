package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BudgetedTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.BudgetedTransactionService;
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
 * This class is the REST controller for the BudgetedTransaction entity.
 */
@RestController
@RequestMapping("/budgetedTransaction")
@RequiredArgsConstructor
@Getter
@Setter
public class BudgetedTransactionController {

    private final BudgetedTransactionService budgetedTransactionService;
    private final UserAccountUtil userAccountUtil;


    // Write a method to return all BudgetedTransaction objects.
    @Operation(summary = "Get all BudgetedTransactions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all BudgetedTransactions", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetedTransaction.class)) }) })
    @RequestMapping("/getAllBudgetedTransactions")
    public Page<BudgetedTransaction> getAllBudgetedTransactions(Principal principal, 
                        @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return budgetedTransactionService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save BudgetedTransaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetedTransaction saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetedTransaction.class)) }) })
    @RequestMapping("/saveBudgetedTransaction")
    public BudgetedTransaction saveBudgetedTransaction(
            @Parameter(description = "BudgetedTransaction to be saved", required = true) BudgetedTransaction budgetedTransaction,
            Principal principal) {
        return budgetedTransactionService.save(budgetedTransaction);
    }

    @Operation(summary = "Delete BudgetedTransaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetedTransaction deleted") })
    @RequestMapping("/deleteBudgetedTransaction")
    public void deleteBudgetedTransaction(
            @Parameter(description = "BudgetedTransaction to be deleted", required = true) BudgetedTransaction budgetedTransaction,
            Principal principal) {
                budgetedTransactionService.delete(budgetedTransaction);
    }

}
