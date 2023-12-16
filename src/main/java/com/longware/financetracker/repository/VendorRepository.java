package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface VendorRepository extends EntityRepositoryInterface<Vendor, Long> {

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(Vendor vendor) {
        return existsByDescriptionAndUserAccount(vendor.getDescription(), vendor.getUserAccount());
    }

}
