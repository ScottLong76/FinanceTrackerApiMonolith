package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.repository.DepositRepository;
import com.longware.financetracker.service.BankTransactionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get Deposit by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation"),
        @ApiResponse(responseCode = "404", description = "Deposit not found")
    })
    @RequestMapping("/getDepositById")
    public Deposit getDepositById(@Parameter(description = "Deposit ID") Long id, Principal principal) {
        return depositRepository.findById(id).orElse(null);
    }

    // Write a method to return all Deposit objects.
    @Operation(summary = "Get all Deposits")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/getAllDeposits")
    public Iterable<Deposit> getAllDeposits(Principal principal) {
        return depositRepository.findAll();
    }

    @Operation(summary = "Save Deposit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/saveDeposit")
    public Deposit saveDeposit(@Parameter(description = "Deposit object") Deposit deposit, Principal principal) {
        return depositRepository.save(deposit);
    }

    @Operation(summary = "Delete Deposit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/deleteDeposit")
    public void deleteDeposit(@Parameter(description = "Deposit object") Deposit deposit, Principal principal) {
        depositRepository.delete(deposit);
    }

    @Operation(summary = "Update Bank Transactions by Deposit")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation")
    })
    @RequestMapping("/updateBankTransactionsByDeposit")
    public Deposit updateBankTransactionsByDeposit(@Parameter(description = "Deposit ID") Long depositId, Principal principal) {
        Deposit deposit = depositRepository.findById(depositId).orElse(null);
        if (deposit != null) {
            bankTransactionService.updateBankTransactionsByEntityAndRegex(deposit,
                    deposit.getDepositMatchingRules().stream().map(rule -> rule.getDepositRegexCriteria()).toList(),
                    deposit.getUserAccount());
        }
        return deposit;
    }

}
