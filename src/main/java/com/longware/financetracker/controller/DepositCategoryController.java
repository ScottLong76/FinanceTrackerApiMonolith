package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.repository.DepositCategoryRepository;

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
 * This class is the REST controller for the DepositCategory entity.
 */
@RestController
@RequestMapping("/depositCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class DepositCategoryController {

    private final DepositCategoryRepository depositCategoryRepository;

    // Write methods to create, update, and delete DepositCategory objects using
    // available methods in the DepositCategoryRepository interface.

    // Write a method to return a DepositCategory object by its id.
    @Operation(summary = "Get DepositCategory by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepositCategory.class))),
        @ApiResponse(responseCode = "404", description = "DepositCategory not found")
    })
    @RequestMapping("/getDepositCategoryById")
    public DepositCategory getDepositCategoryById(
        @Parameter(description = "ID of the DepositCategory to be obtained", required = true) Long id, 
        Principal principal) {
        return depositCategoryRepository.findById(id).orElse(null);
    }

    // Write a method to return all DepositCategory objects.
    @Operation(summary = "Get all DepositCategories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepositCategory.class)))
    })
    @RequestMapping("/getAllDepositCategories")
    public Iterable<DepositCategory> getAllDepositCategories(Principal principal) {
        return depositCategoryRepository.findAll();
    }

    @Operation(summary = "Save DepositCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepositCategory.class)))
    })
    @RequestMapping("/saveDepositCategory")
    public DepositCategory saveDepositCategory(DepositCategory depositCategory, Principal principal) {
        return depositCategoryRepository.save(depositCategory);
    }

    @Operation(summary = "Delete DepositCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "DepositCategory not found")
    })
    @RequestMapping("/deleteDepositCategory")
    public void deleteDepositCategory(DepositCategory depositCategory, Principal principal) {
        depositCategoryRepository.delete(depositCategory);
    }

}
