package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Bank;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.BankService;
import com.longware.financetracker.util.UserAccountUtil;

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

    private final BankService bankService;
    private final UserAccountUtil userAccountUtil;

    /**
     * This method returns a Bank object by its id.
     * 
     * @param id        The id of the Bank object to return.
     * @param principal The principal object representing the authenticated user.
     * @return The Bank object with the given id.
     */
    @RequestMapping("/getBankById")
    public Bank getBankById(Long id, Principal principal) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return bankService.findById(id).orElse(null);
    }

    /**
     * This method returns all Bank objects.
     * 
     * @param principal The principal object representing the authenticated user.
     * @return A list of all Bank objects.
     */
    @RequestMapping("/getAllBanks")
    public Iterable<Bank> getAllBanks(Principal principal) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return bankService.findAll();
    }

    /**
     * This method saves a Bank object.
     * 
     * @param bank      The Bank object to save.
     * @param principal The principal object representing the authenticated user.
     * @return The saved Bank object.
     */
    @RequestMapping("/saveBank")
    public Bank saveBank(Bank bank, Principal principal) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return bankService.save(bank);
    }

    /**
     * This method deletes a Bank object.
     * 
     * @param bank      The Bank object to delete.
     * @param principal The principal object representing the authenticated user.
     */
    @RequestMapping("/deleteBank")
    public void deleteBank(Bank bank, Principal principal) {
        UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        bankService.delete(bank);
    }

}
