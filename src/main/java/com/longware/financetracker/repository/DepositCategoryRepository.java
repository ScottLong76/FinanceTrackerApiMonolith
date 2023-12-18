package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface DepositCategoryRepository extends EntityRepositoryInterface<DepositCategory, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public Optional<DepositCategory> findByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(DepositCategory depositCategory) {
        return existsByDescriptionAndUserAccount(depositCategory.getDescription(), depositCategory.getUserAccount());
    }

    public default Optional<DepositCategory> getEntity(DepositCategory depositCategory) {
        return findByDescriptionAndUserAccount(depositCategory.getDescription(), depositCategory.getUserAccount());
    }

}
