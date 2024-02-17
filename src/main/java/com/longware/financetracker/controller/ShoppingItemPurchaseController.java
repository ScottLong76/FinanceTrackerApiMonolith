package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ShoppingItemPurchase;
import com.longware.financetracker.service.ShoppingItemPurchaseService;

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
 * This class is the REST controller for the ShoppingItemPurchase entity.
 */
@RestController
@RequestMapping("/shoppingItemPurchase")
@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingItemPurchaseController {

    private final ShoppingItemPurchaseService shoppingItemPurchaseService;

    // Write methods to create, update, and delete ShoppingItemPurchase objects
    // using
    // available methods in the ShoppingItemPurchaseRepository interface.

    // Write a method to return a ShoppingItemPurchase object by its id.
    @Operation(summary = "Get ShoppingItemPurchase by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = ShoppingItemPurchase.class))),
        @ApiResponse(responseCode = "404", description = "ShoppingItemPurchase not found")
    })
    @RequestMapping("/getShoppingItemPurchaseById")
    public ShoppingItemPurchase getShoppingItemPurchaseById(
        @Parameter(description = "ID of the ShoppingItemPurchase to be obtained", required = true)
        Long id, Principal principal) {
        return shoppingItemPurchaseService.findById(id).orElse(null);
    }

    @Operation(summary = "Save ShoppingItemPurchase")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = ShoppingItemPurchase.class)))
    })
    @RequestMapping("/saveShoppingItemPurchase")
    public ShoppingItemPurchase saveShoppingItemPurchase(
        @Parameter(description = "ShoppingItemPurchase object to be saved", required = true)
        ShoppingItemPurchase shoppingItemPurchase, Principal principal) {
        return shoppingItemPurchaseService.save(shoppingItemPurchase);
    }

}
