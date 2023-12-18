package com.longware.financetracker.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.repository.BankRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the Bank entity.
 */
@RestController
@RequestMapping("/bank")
@RequiredArgsConstructor
@Getter
@Setter
public class BankController {

    private final BankRepository bankRepository;

    /**
     * This method returns a Bank object by its id.
     * 
     * @param id The id of the Bank object to return.
     * @return The Bank object with the given id.
     */
    @RequestMapping("/getBankById")
    public Bank getBankById(Long id) {
        return bankRepository.findById(id).orElse(null);
    }

    /**
     * This method returns all Bank objects.
     * 
     * @return A list of all Bank objects.
     */
    @RequestMapping("/getAllBanks")
    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    @RequestMapping("/saveBank")
    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    @RequestMapping("/deleteBank")
    public void deleteBank(Bank bank) {
        bankRepository.delete(bank);
    }

}
