package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.DepositMatchingRule;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface DepositMatchingRuleRepository extends EntityRepositoryInterface<DepositMatchingRule, Long> {

    public boolean existsByExecutionOrderAndDepositLikeCriteriaAndDepositRegexCriteriaAndUserAccount(
            Integer executionOrder, String depositLikeCriteria, String depositRegexCriteria, UserAccount userAccount);

    public default boolean entityExists(DepositMatchingRule depositMatchingRule) {
        return existsByExecutionOrderAndDepositLikeCriteriaAndDepositRegexCriteriaAndUserAccount(
                depositMatchingRule.getExecutionOrder(), depositMatchingRule.getDepositLikeCriteria(),
                depositMatchingRule.getDepositRegexCriteria(), depositMatchingRule.getUserAccount());
    }
}
