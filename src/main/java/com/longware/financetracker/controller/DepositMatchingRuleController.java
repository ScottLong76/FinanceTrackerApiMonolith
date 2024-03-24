package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.DepositMatchingRule;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.DepositMatchingRuleService;
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
 * This class is the REST controller for the DepositMatchingRule entity.
 */
@RestController
@RequestMapping("/depositMatchingRule")
@RequiredArgsConstructor
@Getter
@Setter
public class DepositMatchingRuleController {

    private final DepositMatchingRuleService depositMatchingRuleService;
    private final UserAccountUtil userAccountUtil;

    // Write methods to create, update, and delete DepositMatchingRule objects using
    // available methods in the DepositMatchingRuleRepository interface.

    // Write a method to return a DepositMatchingRule object by its id.
    @Operation(summary = "Get DepositMatchingRule by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = DepositMatchingRule.class))),
        @ApiResponse(responseCode = "404", description = "DepositMatchingRule not found")
    })
    @RequestMapping("/getDepositMatchingRuleById")
    public DepositMatchingRule getDepositMatchingRuleById(
            @Parameter(description = "Id of the DepositMatchingRule") Long id, Principal principal) {
        return depositMatchingRuleService.findById(id).orElse(null);
    }

    // Write a method to return all DepositMatchingRule objects.
    @Operation(summary = "Get all DepositMatchingRules")
    @ApiResponse(responseCode = "200", description = "Successful operation", 
        content = @Content(schema = @Schema(implementation = DepositMatchingRule.class, 
            type = "array")))
    @RequestMapping("/getAllDepositMatchingRules")
    public Page<DepositMatchingRule> getAllDepositMatchingRules(Principal principal,
            @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return depositMatchingRuleService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save DepositMatchingRule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = DepositMatchingRule.class))),
        @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @RequestMapping("/saveDepositMatchingRule")
    public DepositMatchingRule saveDepositMatchingRule(
            @Parameter(description = "DepositMatchingRule object to be saved") 
            DepositMatchingRule depositMatchingRule, Principal principal) {
        return depositMatchingRuleService.save(depositMatchingRule);
    }

    @Operation(summary = "Delete DepositMatchingRule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "DepositMatchingRule not found")
    })
    @RequestMapping("/deleteDepositMatchingRule")
    public void deleteDepositMatchingRule(
            @Parameter(description = "DepositMatchingRule object to be deleted") 
            DepositMatchingRule depositMatchingRule, Principal principal) {
                depositMatchingRuleService.delete(depositMatchingRule);
    }

}
