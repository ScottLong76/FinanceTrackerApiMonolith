package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ShoppingItem;
import com.longware.financetracker.repository.ShoppingItemRepository;

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
 * This class is the REST controller for the ShoppingItem entity.
 */
@RestController
@RequestMapping("/shoppingItem")
@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingItemController {

    private final ShoppingItemRepository shoppingItemRepository;

    // Write methods to create, update, and delete ShoppingItem objects using
    // available methods in the ShoppingItemRepository interface.

    // Write a method to return a ShoppingItem object by its id.
    @Operation(summary = "Get a ShoppingItem by id")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the ShoppingItem", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ShoppingItem.class)) }),
        @ApiResponse(responseCode = "404", description = "ShoppingItem not found") })
    @RequestMapping("/getShoppingItemById")
    public ShoppingItem getShoppingItemById(
        @Parameter(description = "Id of the ShoppingItem to be obtained", required = true) Long id, 
        Principal principal) {
        return shoppingItemRepository.findById(id).orElse(null);
    }

    // Write a method to return all ShoppingItem objects.
    @Operation(summary = "Get all ShoppingItems")
    @ApiResponse(responseCode = "200", description = "List of ShoppingItems", 
        content = { @Content(mediaType = "application/json", 
            schema = @Schema(implementation = ShoppingItem.class)) })
    @RequestMapping("/getAllShoppingItems")
    public Iterable<ShoppingItem> getAllShoppingItems(Principal principal) {
        return shoppingItemRepository.findAll();
    }

    @Operation(summary = "Save a ShoppingItem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ShoppingItem saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = ShoppingItem.class)) }) })
    @RequestMapping("/saveShoppingItem")
    public ShoppingItem saveShoppingItem(ShoppingItem shoppingItem, Principal principal) {
        return shoppingItemRepository.save(shoppingItem);
    }

    @Operation(summary = "Delete a ShoppingItem")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "ShoppingItem deleted") })
    @RequestMapping("/deleteShoppingItem")
    public void deleteShoppingItem(ShoppingItem shoppingItem, Principal principal) {
        shoppingItemRepository.delete(shoppingItem);
    }

}
