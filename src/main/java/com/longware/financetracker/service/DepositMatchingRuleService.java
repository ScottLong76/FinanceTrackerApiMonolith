package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.DepositMatchingRule;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.DepositMatchingRuleRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to provide services for the DepositMatchingRule entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DepositMatchingRuleService {

    private final DepositMatchingRuleRepository depositMatchingRuleRepository;

    // Write methods to interact with all methods currently defined in the
    // DepositMatchingRuleRepository

    public void deleteAll() {
        depositMatchingRuleRepository.deleteAll();
    }

    public void deleteById(Long id) {
        depositMatchingRuleRepository.deleteById(id);
    }

    public void delete(DepositMatchingRule depositMatchingRule) {
        depositMatchingRuleRepository.delete(depositMatchingRule);
    }

    public void saveAll(Iterable<DepositMatchingRule> depositMatchingRules) {
        depositMatchingRuleRepository.saveAll(depositMatchingRules);
    }

    public DepositMatchingRule save(DepositMatchingRule depositMatchingRule) {
        return depositMatchingRuleRepository.save(depositMatchingRule);
    }

    public Optional<DepositMatchingRule> findById(Long id) {
        return depositMatchingRuleRepository.findById(id);
    }

    public Iterable<DepositMatchingRule> findAll() {
        return depositMatchingRuleRepository.findAll();
    }

    public Iterable<DepositMatchingRule> findAllByUserAccount(UserAccount userAccount) {
        return depositMatchingRuleRepository.findAllByUserAccount(userAccount);
    }

    public Page<DepositMatchingRule> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return depositMatchingRuleRepository.findPageByUserAccount(userAccount, pageable);
    }

}
