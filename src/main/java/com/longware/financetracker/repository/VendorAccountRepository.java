package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.entities.VendorAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface VendorAccountRepository extends EntityRepositoryInterface<VendorAccount, Long> {

    public boolean existsByVendorAndUserAccount(Vendor vendor, UserAccount userAccount);

    public default boolean entityExists(VendorAccount vendorAccount) {
        return existsByVendorAndUserAccount(vendorAccount.getVendor(), vendorAccount.getUserAccount());
    }

}
