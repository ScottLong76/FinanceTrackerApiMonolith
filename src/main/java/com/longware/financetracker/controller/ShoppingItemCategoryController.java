package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.ShoppingItemCategory;
import com.longware.financetracker.repository.ShoppingItemCategoryRepository;

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
    @RequestMapping("/getShoppingItemCategoryById")
    public ShoppingItemCategory getShoppingItemCategoryById(Long id, Principal principal) {
        return shoppingItemCategoryRepository.findById(id).orElse(null);
    }

    // Write a method to return all ShoppingItemCategory objects.
    @RequestMapping("/getAllShoppingItemCategories")
    public Iterable<ShoppingItemCategory> getAllShoppingItemCategories(Principal principal) {
        return shoppingItemCategoryRepository.findAll();
    }

    @RequestMapping("/saveShoppingItemCategory")
    public ShoppingItemCategory saveShoppingItemCategory(ShoppingItemCategory shoppingItemCategory,
            Principal principal) {
        return shoppingItemCategoryRepository.save(shoppingItemCategory);
    }

    @RequestMapping("/deleteShoppingItemCategory")
    public void deleteShoppingItemCategory(ShoppingItemCategory shoppingItemCategory, Principal principal) {
        shoppingItemCategoryRepository.delete(shoppingItemCategory);
    }

}
