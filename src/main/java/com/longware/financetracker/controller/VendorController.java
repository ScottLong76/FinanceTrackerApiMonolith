package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.repository.VendorRepository;

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

    private final VendorRepository vendorRepository;

    // Write methods to create, update, and delete Vendor objects using available
    // methods in the VendorRepository interface.

    // Write a method to return a Vendor object by its id.
    @RequestMapping("/getVendorById")
    public Vendor getVendorById(Long id) {
        return vendorRepository.findById(id).orElse(null);
    }

    // Write a method to return all Vendor objects.
    @RequestMapping("/getAllVendors")
    public Iterable<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @RequestMapping("/saveVendor")
    public Vendor saveVendor(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    @RequestMapping("/deleteVendor")
    public void deleteVendor(Vendor vendor) {
        vendorRepository.delete(vendor);
    }

}
