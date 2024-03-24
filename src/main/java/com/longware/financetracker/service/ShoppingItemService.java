package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ShoppingItem;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.ShoppingItemRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to handle the business logic for the ShoppingItem entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingItemService {

    private final ShoppingItemRepository shoppingItemRepository;

    // Write methods to interact with all methods currently defined in the
    // ShoppingItemRepository

    public void deleteAll() {
        shoppingItemRepository.deleteAll();
    }

    public void deleteById(Long id) {
        shoppingItemRepository.deleteById(id);
    }

    public void saveAll(Iterable<ShoppingItem> shoppingItems) {
        shoppingItemRepository.saveAll(shoppingItems);
    }

    public ShoppingItem save(ShoppingItem shoppingItem) {
        return shoppingItemRepository.save(shoppingItem);
    }

    public Iterable<ShoppingItem> findAll() {
        return shoppingItemRepository.findAll();
    }

    public Optional<ShoppingItem> findById(Long id) {
        return shoppingItemRepository.findById(id);
    }

    public Iterable<ShoppingItem> findAllByUserAccount(UserAccount userAccount) {
        return shoppingItemRepository.findAllByUserAccount(userAccount);
    }

    public Page<ShoppingItem> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return shoppingItemRepository.findPageByUserAccount(userAccount, pageable);
    }

}
