package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BudgetedTransaction;
import com.longware.financetracker.repository.BudgetedTransactionRepository;

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

    private final BudgetedTransactionRepository budgetedTransactionRepository;

    // Write methods to create, update, and delete BudgetedTransaction objects using
    // available methods in the BudgetedTransactionRepository interface.

    // Write a method to return a BudgetedTransaction object by its id.
    @Operation(summary = "Get BudgetedTransaction by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the BudgetedTransaction", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetedTransaction.class)) }),
        @ApiResponse(responseCode = "404", description = "BudgetedTransaction not found") })
    @RequestMapping("/getBudgetedTransactionById")
    public BudgetedTransaction getBudgetedTransactionById(
            @Parameter(description = "ID of the BudgetedTransaction to be obtained", required = true) Long id,
            Principal principal) {
        return budgetedTransactionRepository.findById(id).orElse(null);
    }

    // Write a method to return all BudgetedTransaction objects.
    @Operation(summary = "Get all BudgetedTransactions")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all BudgetedTransactions", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetedTransaction.class)) }) })
    @RequestMapping("/getAllBudgetedTransactions")
    public Iterable<BudgetedTransaction> getAllBudgetedTransactions(Principal principal) {
        return budgetedTransactionRepository.findAll();
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
        return budgetedTransactionRepository.save(budgetedTransaction);
    }

    @Operation(summary = "Delete BudgetedTransaction")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetedTransaction deleted") })
    @RequestMapping("/deleteBudgetedTransaction")
    public void deleteBudgetedTransaction(
            @Parameter(description = "BudgetedTransaction to be deleted", required = true) BudgetedTransaction budgetedTransaction,
            Principal principal) {
        budgetedTransactionRepository.delete(budgetedTransaction);
    }

}
