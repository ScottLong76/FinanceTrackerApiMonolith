package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.VendorAccount;
import com.longware.financetracker.repository.VendorAccountRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the VendorAccount entity.
 */
@RestController
@RequestMapping("/vendorAccount")
@RequiredArgsConstructor
@Getter
@Setter
public class VendorAccountController {

    private final VendorAccountRepository vendorAccountRepository;

    // Write methods to create, update, and delete VendorAccount objects using
    // available methods in the VendorAccountRepository interface.

    // Write a method to return a VendorAccount object by its id.
    @RequestMapping("/getVendorAccountById")
    public VendorAccount getVendorAccountById(Long id) {
        return vendorAccountRepository.findById(id).orElse(null);
    }

    // Write a method to return all VendorAccount objects.
    @RequestMapping("/getAllVendorAccounts")
    public Iterable<VendorAccount> getAllVendorAccounts(Principal principal) {
        return vendorAccountRepository.findAll();
    }

    @RequestMapping("/saveVendorAccount")
    public VendorAccount saveVendorAccount(VendorAccount vendorAccount, Principal principal) {
        return vendorAccountRepository.save(vendorAccount);
    }

    @RequestMapping("/deleteVendorAccount")
    public void deleteVendorAccount(VendorAccount vendorAccount, Principal principal) {
        vendorAccountRepository.delete(vendorAccount);
    }

}
