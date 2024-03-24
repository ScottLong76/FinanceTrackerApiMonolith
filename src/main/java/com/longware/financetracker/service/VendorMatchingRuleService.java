package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.VendorMatchingRule;
import com.longware.financetracker.repository.VendorMatchingRuleRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to provide the business logic for the VendorMatchingRule
 * entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class VendorMatchingRuleService {

    private final VendorMatchingRuleRepository vendorMatchingRuleRepository;

    // Write methods to interact with all methods currently defined in the
    // VendorMatchingRuleRepository

    public void deleteAll() {
        vendorMatchingRuleRepository.deleteAll();
    }

    public void deleteById(Long id) {
        vendorMatchingRuleRepository.deleteById(id);
    }

    public void saveAll(Iterable<VendorMatchingRule> vendorMatchingRules) {
        vendorMatchingRuleRepository.saveAll(vendorMatchingRules);
    }

    public VendorMatchingRule save(VendorMatchingRule vendorMatchingRule) {
        return vendorMatchingRuleRepository.save(vendorMatchingRule);
    }

    public Iterable<VendorMatchingRule> findAll() {
        return vendorMatchingRuleRepository.findAll();
    }

    public Optional<VendorMatchingRule> findById(Long id) {
        return vendorMatchingRuleRepository.findById(id);
    }

    public Iterable<VendorMatchingRule> findAllByUserAccount(UserAccount userAccount) {
        return vendorMatchingRuleRepository.findAllByUserAccount(userAccount);
    }

    public Page<VendorMatchingRule> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return vendorMatchingRuleRepository.findPageByUserAccount(userAccount, pageable);
    }

}
