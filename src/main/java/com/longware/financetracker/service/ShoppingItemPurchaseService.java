package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.ShoppingItemPurchase;
import com.longware.financetracker.entities.UserAccount;
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

    public Iterable<ShoppingItemPurchase> findAllByUserAccount(UserAccount userAccount) {
        return shoppingItemPurchaseRepository.findAllByUserAccount(userAccount);
    }

    public Page<ShoppingItemPurchase> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return shoppingItemPurchaseRepository.findPageByUserAccount(userAccount, pageable);
    }

}
