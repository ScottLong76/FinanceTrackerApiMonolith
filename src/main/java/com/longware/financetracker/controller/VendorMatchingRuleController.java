package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.VendorMatchingRule;
import com.longware.financetracker.repository.VendorMatchingRuleRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the VendorMatchingRule entity.
 */
@RestController
@RequestMapping("/vendorMatchingRule")
@RequiredArgsConstructor
@Getter
@Setter
public class VendorMatchingRuleController {

    private final VendorMatchingRuleRepository vendorMatchingRuleRepository;

    // Write methods to create, update, and delete VendorMatchingRule objects using
    // available methods in the VendorMatchingRuleRepository interface.

    // Write a method to return a VendorMatchingRule object by its id.
    @RequestMapping("/getVendorMatchingRuleById")
    public VendorMatchingRule getVendorMatchingRuleById(Long id) {
        return vendorMatchingRuleRepository.findById(id).orElse(null);
    }

    // Write a method to return all VendorMatchingRule objects.
    @RequestMapping("/getAllVendorMatchingRules")
    public Iterable<VendorMatchingRule> getAllVendorMatchingRules() {
        return vendorMatchingRuleRepository.findAll();
    }

    @RequestMapping("/saveVendorMatchingRule")
    public VendorMatchingRule saveVendorMatchingRule(VendorMatchingRule vendorMatchingRule) {
        return vendorMatchingRuleRepository.save(vendorMatchingRule);
    }

    @RequestMapping("/deleteVendorMatchingRule")
    public void deleteVendorMatchingRule(VendorMatchingRule vendorMatchingRule) {
        vendorMatchingRuleRepository.delete(vendorMatchingRule);
    }

}
