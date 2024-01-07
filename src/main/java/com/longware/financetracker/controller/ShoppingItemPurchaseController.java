package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ShoppingItemPurchase;
import com.longware.financetracker.service.ShoppingItemPurchaseService;

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
    @RequestMapping("/getShoppingItemPurchaseById")
    public ShoppingItemPurchase getShoppingItemPurchaseById(Long id, Principal principal) {
        return shoppingItemPurchaseService.findById(id).orElse(null);
    }

    @RequestMapping("/saveShoppingItemPurchase")
    public ShoppingItemPurchase saveShoppingItemPurchase(ShoppingItemPurchase shoppingItemPurchase,
            Principal principal) {
        return shoppingItemPurchaseService.save(shoppingItemPurchase);
    }

}
