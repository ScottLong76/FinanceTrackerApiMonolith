package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.DepositMatchingRule;
import com.longware.financetracker.repository.DepositMatchingRuleRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the DepositMatchingRule entity.
 */
@RestController
@RequestMapping("/depositMatchingRule")
@RequiredArgsConstructor
@Getter
@Setter
public class DepositMatchingRuleController {

    private final DepositMatchingRuleRepository depositMatchingRuleRepository;

    // Write methods to create, update, and delete DepositMatchingRule objects using
    // available methods in the DepositMatchingRuleRepository interface.

    // Write a method to return a DepositMatchingRule object by its id.
    @RequestMapping("/getDepositMatchingRuleById")
    public DepositMatchingRule getDepositMatchingRuleById(Long id, Principal principal) {
        return depositMatchingRuleRepository.findById(id).orElse(null);
    }

    // Write a method to return all DepositMatchingRule objects.
    @RequestMapping("/getAllDepositMatchingRules")
    public Iterable<DepositMatchingRule> getAllDepositMatchingRules(Principal principal) {
        return depositMatchingRuleRepository.findAll();
    }

    @RequestMapping("/saveDepositMatchingRule")
    public DepositMatchingRule saveDepositMatchingRule(DepositMatchingRule depositMatchingRule, Principal principal) {
        return depositMatchingRuleRepository.save(depositMatchingRule);
    }

    @RequestMapping("/deleteDepositMatchingRule")
    public void deleteDepositMatchingRule(DepositMatchingRule depositMatchingRule, Principal principal) {
        depositMatchingRuleRepository.delete(depositMatchingRule);
    }

}
