package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.ShoppingItemPurchase;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ShoppingItemPurchaseRepository extends EntityRepositoryInterface<ShoppingItemPurchase, Long> {

    public Iterable<ShoppingItemPurchase> findAllByUserAccount(UserAccount userAccount);

    public Iterable<ShoppingItemPurchase> findAllByShoppingItemDescriptionAndUserAccount(String description,
            UserAccount userAccount);

    public Optional<ShoppingItemPurchase> findByShoppingItemDescriptionAndUserAccountAndBankTransaction(
            String description,
            UserAccount userAccount, BankTransaction bankTransaction);

    public boolean existsByShoppingItemDescriptionAndUserAccountAndBankTransaction(String description,
            UserAccount userAccount, BankTransaction bankTransaction);

    public default boolean entityExists(ShoppingItemPurchase shoppingItemPurchase) {
        return existsByShoppingItemDescriptionAndUserAccountAndBankTransaction(
                shoppingItemPurchase.getShoppingItem().getDescription(),
                shoppingItemPurchase.getShoppingItem().getUserAccount(),
                shoppingItemPurchase.getBankTransaction());
    }

    public default Iterable<ShoppingItemPurchase> getEntities(ShoppingItemPurchase shoppingItemPurchase) {
        return findAllByShoppingItemDescriptionAndUserAccount(
                shoppingItemPurchase.getShoppingItem().getDescription(),
                shoppingItemPurchase.getShoppingItem().getUserAccount());
    }

    public default Optional<ShoppingItemPurchase> getEntity(ShoppingItemPurchase shoppingItemPurchase) {
        return findByShoppingItemDescriptionAndUserAccountAndBankTransaction(
                shoppingItemPurchase.getShoppingItem().getDescription(),
                shoppingItemPurchase.getShoppingItem().getUserAccount(),
                shoppingItemPurchase.getBankTransaction());
    }

}