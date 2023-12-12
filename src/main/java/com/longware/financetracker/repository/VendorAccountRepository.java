package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.VendorAccount;

@Repository
public interface VendorAccountRepository extends JpaRepository<VendorAccount, Long> {

}
