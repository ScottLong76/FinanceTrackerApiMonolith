package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ShoppingItem;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ShoppingItemRepository extends EntityRepositoryInterface<ShoppingItem, Long> {

    public Iterable<ShoppingItem> findAllByUserAccount(UserAccount userAccount);

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public Optional<ShoppingItem> findByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(ShoppingItem shoppingItem) {
        return existsByDescriptionAndUserAccount(shoppingItem.getDescription(), shoppingItem.getUserAccount());
    }

    public default Optional<ShoppingItem> getEntity(ShoppingItem shoppingItem) {
        return findByDescriptionAndUserAccount(shoppingItem.getDescription(), shoppingItem.getUserAccount());
    }

}
