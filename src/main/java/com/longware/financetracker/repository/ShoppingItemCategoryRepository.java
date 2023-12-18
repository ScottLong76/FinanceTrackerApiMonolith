package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ShoppingItemCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface ShoppingItemCategoryRepository extends EntityRepositoryInterface<ShoppingItemCategory, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public Optional<ShoppingItemCategory> findByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(ShoppingItemCategory shoppingItemCategory) {
        return existsByDescriptionAndUserAccount(shoppingItemCategory.getDescription(),
                shoppingItemCategory.getUserAccount());
    }

    public default Optional<ShoppingItemCategory> getEntity(ShoppingItemCategory shoppingItemCategory) {
        return findByDescriptionAndUserAccount(shoppingItemCategory.getDescription(),
                shoppingItemCategory.getUserAccount());
    }

}
