package com.longware.financetracker.service;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.VendorAccount;
import com.longware.financetracker.repository.VendorAccountRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Optional;

/**
 * This class is used to handle the business logic for the VendorAccount entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class VendorAccountService {

    private final VendorAccountRepository vendorAccountRepository;

    // Write methods to interact with all methods currently defined in the
    // VendorAccountRepository

    public void deleteAll() {
        vendorAccountRepository.deleteAll();
    }

    public void deleteById(Long id) {
        vendorAccountRepository.deleteById(id);
    }

    public void saveAll(Iterable<VendorAccount> vendorAccounts) {
        vendorAccountRepository.saveAll(vendorAccounts);
    }

    public void save(VendorAccount vendorAccount) {
        vendorAccountRepository.save(vendorAccount);
    }

    public Iterable<VendorAccount> findAll() {
        return vendorAccountRepository.findAll();
    }

    public Optional<VendorAccount> findById(Long id) {
        return vendorAccountRepository.findById(id);
    }

}
