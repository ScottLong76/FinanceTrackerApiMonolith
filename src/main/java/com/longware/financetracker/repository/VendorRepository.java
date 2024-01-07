package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface VendorRepository extends EntityRepositoryInterface<Vendor, Long> {

    public Iterable<Vendor> findAllByUserAccount(UserAccount userAccount);

    public boolean existsByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public Optional<Vendor> findByDescriptionAndUserAccount(String description, UserAccount userAccount);

    public default boolean entityExists(Vendor vendor) {
        return existsByDescriptionAndUserAccount(vendor.getDescription(), vendor.getUserAccount());
    }

    public default Optional<Vendor> getEntity(Vendor vendor) {
        return findByDescriptionAndUserAccount(vendor.getDescription(), vendor.getUserAccount());
    }

}
