package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.VendorMatchingRule;
import com.longware.financetracker.repository.VendorMatchingRuleRepository;

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
 * This class is the REST controller for the VendorMatchingRule entity.
 */
@RestController
@RequestMapping("/vendorMatchingRule")
@RequiredArgsConstructor
@Getter
@Setter
public class VendorMatchingRuleController {

    private final VendorMatchingRuleRepository vendorMatchingRuleRepository;

    // Write methods to create, update, and delete VendorMatchingRule objects using
    // available methods in the VendorMatchingRuleRepository interface.

    // Write a method to return a VendorMatchingRule object by its id.
    @Operation(summary = "Get VendorMatchingRule by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendorMatchingRule.class))),
        @ApiResponse(responseCode = "404", description = "VendorMatchingRule not found")
    })
    @RequestMapping("/getVendorMatchingRuleById")
    public VendorMatchingRule getVendorMatchingRuleById(
        @Parameter(description = "ID of the VendorMatchingRule") Long id, Principal principal) {
        return vendorMatchingRuleRepository.findById(id).orElse(null);
    }

    // Write a method to return all VendorMatchingRule objects.
    @Operation(summary = "Get all VendorMatchingRules")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendorMatchingRule.class)))
    })
    @RequestMapping("/getAllVendorMatchingRules")
    public Iterable<VendorMatchingRule> getAllVendorMatchingRules(Principal principal) {
        return vendorMatchingRuleRepository.findAll();
    }

    @Operation(summary = "Save VendorMatchingRule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = VendorMatchingRule.class)))
    })
    @RequestMapping("/saveVendorMatchingRule")
    public VendorMatchingRule saveVendorMatchingRule(
        @Parameter(description = "VendorMatchingRule to be saved") VendorMatchingRule vendorMatchingRule, Principal principal) {
        return vendorMatchingRuleRepository.save(vendorMatchingRule);
    }

    @Operation(summary = "Delete VendorMatchingRule")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation")
    })
    @RequestMapping("/deleteVendorMatchingRule")
    public void deleteVendorMatchingRule(
        @Parameter(description = "VendorMatchingRule to be deleted") VendorMatchingRule vendorMatchingRule, Principal principal) {
        vendorMatchingRuleRepository.delete(vendorMatchingRule);
    }

}
