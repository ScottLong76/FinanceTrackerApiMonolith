package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.BudgetEntry;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.BudgetEntryService;
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
 * This class is the REST controller for the BudgetEntry entity.
 */
@RestController
@RequestMapping("/budgetEntry")
@RequiredArgsConstructor
@Getter
@Setter
public class BudgetEntryController {

    private final BudgetEntryService budgetEntryService;
    private final UserAccountUtil userAccountUtil;

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
        return budgetEntryService.findById(id).orElse(null);
    }

    // Write a method to return all BudgetEntry objects.
    @Operation(summary = "Get all BudgetEntries")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all BudgetEntries", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetEntry.class)) }) })
    @RequestMapping("/getAllBudgetEntries")
    public Page<BudgetEntry> getAllBudgetEntries(Principal principal, 
                        @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return budgetEntryService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save BudgetEntry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetEntry saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = BudgetEntry.class)) }) })
    @RequestMapping("/saveBudgetEntry")
    public BudgetEntry saveBudgetEntry(BudgetEntry budgetEntry, Principal principal) {
        return budgetEntryService.save(budgetEntry);
    }

    @Operation(summary = "Delete BudgetEntry")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "BudgetEntry deleted") })
    @RequestMapping("/deleteBudgetEntry")
    public void deleteBudgetEntry(BudgetEntry budgetEntry, Principal principal) {
        budgetEntryService.delete(budgetEntry);
    }

}
