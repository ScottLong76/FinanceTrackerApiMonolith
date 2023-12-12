package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.DepositMatchingRule;

@Repository
public interface DepositMatchingRuleRepository extends JpaRepository<DepositMatchingRule, Long> {

}
