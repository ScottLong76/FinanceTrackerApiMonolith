package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.DepositMatchingRule;
import com.longware.financetracker.repository.DepositMatchingRuleRepository;

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

    private final DepositMatchingRuleRepository depositMatchingRuleRepository;

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
        return depositMatchingRuleRepository.findById(id).orElse(null);
    }

    // Write a method to return all DepositMatchingRule objects.
    @Operation(summary = "Get all DepositMatchingRules")
    @ApiResponse(responseCode = "200", description = "Successful operation", 
        content = @Content(schema = @Schema(implementation = DepositMatchingRule.class, 
            type = "array")))
    @RequestMapping("/getAllDepositMatchingRules")
    public Iterable<DepositMatchingRule> getAllDepositMatchingRules(Principal principal) {
        return depositMatchingRuleRepository.findAll();
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
        return depositMatchingRuleRepository.save(depositMatchingRule);
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
        depositMatchingRuleRepository.delete(depositMatchingRule);
    }

}
