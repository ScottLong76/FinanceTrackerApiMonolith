package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.UserAccountRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the UserAccount entity.
 */
@RestController
@RequestMapping("/userAccount")
@RequiredArgsConstructor
@Getter
@Setter
public class UserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Write methods to create, update, and delete UserAccount objects using
    // available methods in the UserAccountRepository interface.

    // Write a method to return a UserAccount object by its id.
    @RequestMapping("/getUserAccountById")
    public UserAccount getUserAccountById(Long id, Principal principal) {
        return userAccountRepository.findById(id).orElse(null);
    }

    // Write a method to return all UserAccount objects.
    @RequestMapping("/getAllUserAccounts")
    public Iterable<UserAccount> getAllUserAccounts(Principal principal) {
        return userAccountRepository.findAll();
    }

    @RequestMapping("/saveUserAccount")
    public UserAccount saveUserAccount(UserAccount userAccount, Principal principal) {
        return userAccountRepository.save(userAccount);
    }

    @RequestMapping("/deleteUserAccount")
    public void deleteUserAccount(UserAccount userAccount, Principal principal) {
        userAccountRepository.delete(userAccount);
    }

}
