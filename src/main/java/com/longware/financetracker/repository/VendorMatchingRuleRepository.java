package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.entities.VendorMatchingRule;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface VendorMatchingRuleRepository extends EntityRepositoryInterface<VendorMatchingRule, Long> {

    public boolean existsByVendorLikeCriteriaAndVendorRegexCriteriaAndUserAccount(String vendorLikeCriteria,
            String vendorRegexCriteria, UserAccount userAccount);

    public Optional<VendorMatchingRule> findByVendorLikeCriteriaAndVendorRegexCriteriaAndUserAccount(
            String vendorLikeCriteria, String vendorRegexCriteria, UserAccount userAccount);

    public default boolean entityExists(VendorMatchingRule vendorMatchingRule) {
        return existsByVendorLikeCriteriaAndVendorRegexCriteriaAndUserAccount(
                vendorMatchingRule.getVendorLikeCriteria(), vendorMatchingRule.getVendorRegexCriteria(),
                vendorMatchingRule.getUserAccount());
    }

    public default Optional<VendorMatchingRule> getEntity(VendorMatchingRule vendorMatchingRule) {
        return findByVendorLikeCriteriaAndVendorRegexCriteriaAndUserAccount(
                vendorMatchingRule.getVendorLikeCriteria(), vendorMatchingRule.getVendorRegexCriteria(),
                vendorMatchingRule.getUserAccount());
    }

}
