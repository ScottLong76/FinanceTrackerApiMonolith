package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.DepositCategoryService;
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
 * This class is the REST controller for the DepositCategory entity.
 */
@RestController
@RequestMapping("/depositCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class DepositCategoryController {

    private final DepositCategoryService depositCategoryService;
     private final UserAccountUtil userAccountUtil;
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
        return depositCategoryService.findById(id).orElse(null);
    }

    // Write a method to return all DepositCategory objects.
    @Operation(summary = "Get all DepositCategories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepositCategory.class)))
    })
    @RequestMapping("/getAllDepositCategories")
    public Page<DepositCategory> getAllDepositCategories(Principal principal,
                        @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
            
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return depositCategoryService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save DepositCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = DepositCategory.class)))
    })
    @RequestMapping("/saveDepositCategory")
    public DepositCategory saveDepositCategory(DepositCategory depositCategory, Principal principal) {
        return depositCategoryService.save(depositCategory);
    }

    @Operation(summary = "Delete DepositCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "DepositCategory not found")
    })
    @RequestMapping("/deleteDepositCategory")
    public void deleteDepositCategory(DepositCategory depositCategory, Principal principal) {
        depositCategoryService.delete(depositCategory);
    }

}
