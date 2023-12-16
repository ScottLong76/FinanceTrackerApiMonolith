package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ShoppingItemCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ShoppingItemCategoryRepository extends EntityRepositoryInterface<ShoppingItemCategory, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(ShoppingItemCategory shoppingItemCategory) {
        return existsByDescriptionAndUserAccount(shoppingItemCategory.getDescription(),
                shoppingItemCategory.getUserAccount());
    }

}
