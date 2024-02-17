package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ShoppingItemCategory;
import com.longware.financetracker.repository.ShoppingItemCategoryRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the ShoppingItemCategory entity.
 */
@RestController
@RequestMapping("/shoppingItemCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingItemCategoryController {

    private final ShoppingItemCategoryRepository shoppingItemCategoryRepository;

    // Write methods to create, update, and delete ShoppingItemCategory objects
    // using
    // available methods in the ShoppingItemCategoryRepository interface.

    // Write a method to return a ShoppingItemCategory object by its id.
    @Operation(summary = "Get ShoppingItemCategory by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "ShoppingItemCategory not found")
    })
    @RequestMapping("/getShoppingItemCategoryById")
    public ShoppingItemCategory getShoppingItemCategoryById(
            @Parameter(description = "Id of the ShoppingItemCategory") Long id, Principal principal) {
        return shoppingItemCategoryRepository.findById(id).orElse(null);
    }

    // Write a method to return all ShoppingItemCategory objects.
    @Operation(summary = "Get all ShoppingItemCategories")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/getAllShoppingItemCategories")
    public Iterable<ShoppingItemCategory> getAllShoppingItemCategories(Principal principal) {
        return shoppingItemCategoryRepository.findAll();
    }

    @Operation(summary = "Save ShoppingItemCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/saveShoppingItemCategory")
    public ShoppingItemCategory saveShoppingItemCategory(
            @Parameter(description = "ShoppingItemCategory to be saved") ShoppingItemCategory shoppingItemCategory,
            Principal principal) {
        return shoppingItemCategoryRepository.save(shoppingItemCategory);
    }

    @Operation(summary = "Delete ShoppingItemCategory")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/deleteShoppingItemCategory")
    public void deleteShoppingItemCategory(
            @Parameter(description = "ShoppingItemCategory to be deleted") ShoppingItemCategory shoppingItemCategory,
            Principal principal) {
        shoppingItemCategoryRepository.delete(shoppingItemCategory);
    }

}
