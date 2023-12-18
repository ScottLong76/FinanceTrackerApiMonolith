package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ShoppingItemPurchase;
import com.longware.financetracker.repository.ShoppingItemPurchaseRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * 
 * Service class for the ShoppingItemPurchase entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class ShoppingItemPurchaseService {

    private final ShoppingItemPurchaseRepository shoppingItemPurchaseRepository;

    // Write methods to interact with all methods currently defined in the
    // ShoppingItemPurchaseRepository

    public void deleteAll() {
        shoppingItemPurchaseRepository.deleteAll();
    }

    public void deleteById(Long id) {
        shoppingItemPurchaseRepository.deleteById(id);
    }

    public void saveAll(Iterable<ShoppingItemPurchase> shoppingItemPurchases) {
        shoppingItemPurchaseRepository.saveAll(shoppingItemPurchases);
    }

    public ShoppingItemPurchase save(ShoppingItemPurchase shoppingItemPurchase) {
        return shoppingItemPurchaseRepository.save(shoppingItemPurchase);
    }

    public Optional<ShoppingItemPurchase> findById(Long id) {
        return shoppingItemPurchaseRepository.findById(id);
    }

}
