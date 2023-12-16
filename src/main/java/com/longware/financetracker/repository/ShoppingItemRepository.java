package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ShoppingItem;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ShoppingItemRepository extends EntityRepositoryInterface<ShoppingItem, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(ShoppingItem shoppingItem) {
        return existsByDescriptionAndUserAccount(shoppingItem.getDescription(), shoppingItem.getUserAccount());
    }

}
