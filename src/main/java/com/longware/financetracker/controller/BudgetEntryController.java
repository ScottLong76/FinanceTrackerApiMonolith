package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BudgetEntry;
import com.longware.financetracker.repository.BudgetEntryRepository;

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
 * This class is the REST controller for the BudgetEntry entity.
 */
@RestController
@RequestMapping("/budgetEntry")
@RequiredArgsConstructor
@Getter
@Setter
public class BudgetEntryController {

    private final BudgetEntryRepository budgetEntryRepository;

    // Write methods to create, update, and delete BudgetEntry objects using
    // available methods in the BudgetEntryRepository interface.

    // Write a method to return a BudgetEntry object by its id.
    @Operation(summary = "Get BudgetEntry by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the BudgetEntry", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetEntry.class)) }),
        @ApiResponse(responseCode = "404", description = "BudgetEntry not found") })
    @RequestMapping("/getBudgetEntryById")
    public BudgetEntry getBudgetEntryById(@Parameter(description = "BudgetEntry ID") Long id) {
        return budgetEntryRepository.findById(id).orElse(null);
    }

    // Write a method to return all BudgetEntry objects.
    @Operation(summary = "Get all BudgetEntries")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all BudgetEntries", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetEntry.class)) }) })
    @RequestMapping("/getAllBudgetEntries")
    public Iterable<BudgetEntry> getAllBudgetEntries(Principal principal) {
        return budgetEntryRepository.findAll();
    }

    @Operation(summary = "Save BudgetEntry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetEntry saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetEntry.class)) }) })
    @RequestMapping("/saveBudgetEntry")
    public BudgetEntry saveBudgetEntry(BudgetEntry budgetEntry, Principal principal) {
        return budgetEntryRepository.save(budgetEntry);
    }

    @Operation(summary = "Delete BudgetEntry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetEntry deleted") })
    @RequestMapping("/deleteBudgetEntry")
    public void deleteBudgetEntry(BudgetEntry budgetEntry, Principal principal) {
        budgetEntryRepository.delete(budgetEntry);
    }

}
