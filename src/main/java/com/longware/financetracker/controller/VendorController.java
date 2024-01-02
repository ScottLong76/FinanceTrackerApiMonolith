package com.longware.financetracker.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.service.BankTransactionService;
import com.longware.financetracker.service.VendorService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the Vendor entity.
 */
@RestController
@RequestMapping("/vendor")
@RequiredArgsConstructor
@Getter
@Setter
public class VendorController {

    private final VendorService vendorService;

    private final BankTransactionService bankTransactionService;

    // Write methods to create, update, and delete Vendor objects using available
    // methods in the VendorRepository interface.

    // Write a method to return a Vendor object by its id.
    @RequestMapping("/getVendorById")
    public Vendor getVendorById(Long id, Principal principal) {
        return vendorService.findById(id).orElse(null);
    }

    // Write a method to return all Vendor objects.
    @RequestMapping("/getAllVendors")
    public Iterable<Vendor> getAllVendors(Principal principal) {
        return vendorService.findAll();
    }

    @RequestMapping("/saveVendor")
    public Vendor saveVendor(Vendor vendor, Principal principal) {
        return vendorService.save(vendor);
    }

    @RequestMapping("/deleteVendor")
    public void deleteVendor(Vendor vendor, Principal principal) {
        vendorService.delete(vendor);
    }

    @RequestMapping("/updateBankTransactionsByVendor")
    public Vendor updateBankTransactionsByVendor(Long vendorId, Principal principal) {
        Vendor vendor = vendorService.findById(vendorId).orElse(null);
        if (vendor != null) {
            List<String> regexCriteria = vendor.getVendorMatchingRules().stream()
                    .map(rule -> rule.getVendorRegexCriteria())
                    .toList();
            bankTransactionService.updateBankTransactionsByEntityAndRegex(vendor, regexCriteria,
                    vendor.getUserAccount());
        }
        return vendor;
    }

}
