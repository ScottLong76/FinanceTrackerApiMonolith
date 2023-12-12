package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ShoppingItemCategory;
import com.longware.financetracker.repository.ShoppingItemCategoryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to handle the business logic for the ShoppingItemCategory
 * entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingItemCategoryService {

    private final ShoppingItemCategoryRepository shoppingItemCategoryRepository;

    // Write methods to interact with all methods currently defined in the
    // ShoppingItemCategoryRepository

    public void deleteAll() {
        shoppingItemCategoryRepository.deleteAll();
    }

    public void deleteById(Long id) {
        shoppingItemCategoryRepository.deleteById(id);
    }

    public void saveAll(Iterable<ShoppingItemCategory> shoppingItemCategories) {
        shoppingItemCategoryRepository.saveAll(shoppingItemCategories);
    }

    public void save(ShoppingItemCategory shoppingItemCategory) {
        shoppingItemCategoryRepository.save(shoppingItemCategory);
    }

    public Optional<ShoppingItemCategory> findById(Long id) {
        return shoppingItemCategoryRepository.findById(id);
    }

    public Iterable<ShoppingItemCategory> findAll() {
        return shoppingItemCategoryRepository.findAll();
    }

}
