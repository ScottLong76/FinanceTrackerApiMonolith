package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.repository.DepositRepository;

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

    // Write methods to create, update, and delete Deposit objects using available
    // methods in the DepositRepository interface.

    // Write a method to return a Deposit object by its id.
    @RequestMapping("/getDepositById")
    public Deposit getDepositById(Long id) {
        return depositRepository.findById(id).orElse(null);
    }

    // Write a method to return all Deposit objects.
    @RequestMapping("/getAllDeposits")
    public Iterable<Deposit> getAllDeposits() {
        return depositRepository.findAll();
    }

    @RequestMapping("/saveDeposit")
    public Deposit saveDeposit(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    @RequestMapping("/deleteDeposit")
    public void deleteDeposit(Deposit deposit) {
        depositRepository.delete(deposit);
    }

}
