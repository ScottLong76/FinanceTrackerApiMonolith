package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ShoppingItem;
import com.longware.financetracker.repository.ShoppingItemRepository;

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
    @RequestMapping("/getShoppingItemById")
    public ShoppingItem getShoppingItemById(Long id) {
        return shoppingItemRepository.findById(id).orElse(null);
    }

    // Write a method to return all ShoppingItem objects.
    @RequestMapping("/getAllShoppingItems")
    public Iterable<ShoppingItem> getAllShoppingItems() {
        return shoppingItemRepository.findAll();
    }

    @RequestMapping("/saveShoppingItem")
    public ShoppingItem saveShoppingItem(ShoppingItem shoppingItem) {
        return shoppingItemRepository.save(shoppingItem);
    }

    @RequestMapping("/deleteShoppingItem")
    public void deleteShoppingItem(ShoppingItem shoppingItem) {
        shoppingItemRepository.delete(shoppingItem);
    }

}
