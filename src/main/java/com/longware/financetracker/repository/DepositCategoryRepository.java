package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface DepositCategoryRepository extends EntityRepositoryInterface<DepositCategory, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(DepositCategory depositCategory) {
        return existsByDescriptionAndUserAccount(depositCategory.getDescription(), depositCategory.getUserAccount());
    }

}
