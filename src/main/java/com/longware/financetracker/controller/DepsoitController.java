package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.repository.DepositRepository;
import com.longware.financetracker.service.BankTransactionService;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the Deposit entity.
 */
@RestController
@RequestMapping("/deposit")
@RequiredArgsConstructor
@Getter
@Setter
public class DepsoitController {

    private final DepositRepository depositRepository;

    private final BankTransactionService bankTransactionService;

    // Write methods to create, update, and delete Deposit objects using available
    // methods in the DepositRepository interface.

    // Write a method to return a Deposit object by its id.
    @RequestMapping("/getDepositById")
    public Deposit getDepositById(Long id, Principal principal) {
        return depositRepository.findById(id).orElse(null);
    }

    // Write a method to return all Deposit objects.
    @RequestMapping("/getAllDeposits")
    public Iterable<Deposit> getAllDeposits(Principal principal) {
        return depositRepository.findAll();
    }

    @RequestMapping("/saveDeposit")
    public Deposit saveDeposit(Deposit deposit, Principal principal) {
        return depositRepository.save(deposit);
    }

    @RequestMapping("/deleteDeposit")
    public void deleteDeposit(Deposit deposit, Principal principal) {
        depositRepository.delete(deposit);
    }

    @RequestMapping("/updateBankTransactionsByDeposit")
    public Deposit updateBankTransactionsByDeposit(Long depositId, Principal principal) {
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        if (deposit != null) {
            bankTransactionService.updateBankTransactionsByEntityAndRegex(deposit,
                    deposit.getDepositMatchingRules().stream().map(rule -> rule.getDepositRegexCriteria()).toList(),
                    deposit.getUserAccount());
        }
        return deposit;
    }

}
