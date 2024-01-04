package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Vendor;
import com.longware.financetracker.repository.VendorRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to provide services for the Vendor entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class VendorService {

    private final VendorRepository vendorRepository;

    // Write methods to interact with all methods currently defined in the
    // VendorRepository

    public void deleteAll() {
        vendorRepository.deleteAll();
    }

    public void deleteById(Long id) {
        vendorRepository.deleteById(id);
    }

    public void saveAll(Iterable<Vendor> vendors) {
        vendorRepository.saveAll(vendors);
    }

    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }

    public Optional<Vendor> findById(Long id) {
        return vendorRepository.findById(id);
    }

    public Iterable<Vendor> findAll() {
        return vendorRepository.findAll();
    }

    public boolean entityExists(Vendor vendor) {
        return vendorRepository.entityExists(vendor);
    }

    public Optional<Vendor> getEntity(Vendor vendor) {
        return vendorRepository.getEntity(vendor);
    }

    public void delete(Vendor vendor) {
        vendorRepository.delete(vendor);
    }

    public Iterable<Vendor> findAllByUserAccount(UsrAccount userAccount) {
        return vendorRepository.findAllByUserAccount(userAccount);
    }

}
