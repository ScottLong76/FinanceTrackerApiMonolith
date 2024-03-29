package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ShoppingItemCategory;
import com.longware.financetracker.entities.UserAccount;
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

    public ShoppingItemCategory save(ShoppingItemCategory shoppingItemCategory) {
        return shoppingItemCategoryRepository.save(shoppingItemCategory);
    }

    public Optional<ShoppingItemCategory> findById(Long id) {
        return shoppingItemCategoryRepository.findById(id);
    }

    public Iterable<ShoppingItemCategory> findAll() {
        return shoppingItemCategoryRepository.findAll();
    }

    public Iterable<ShoppingItemCategory> findAllByUserAccount(UserAccount userAccount) {
        return shoppingItemCategoryRepository.findAllByUserAccount(userAccount);
    }
    
    public Page<ShoppingItemCategory> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return shoppingItemCategoryRepository.findPageByUserAccount(userAccount, pageable);
    }
}
