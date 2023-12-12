package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.VendorMatchingRule;

@Repository
public interface VendorMatchingRuleRepository extends JpaRepository<VendorMatchingRule, Long> {

}

