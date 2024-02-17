package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.UserAccountRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "User Account", description = "API endpoints for managing user accounts")
public class UserAccountController {

    private final UserAccountRepository userAccountRepository;

    // Write methods to create, update, and delete UserAccount objects using
    // available methods in the UserAccountRepository interface.

    // Write a method to return a UserAccount object by its id.
    @Operation(summary = "Get User Account by ID", description = "Returns a UserAccount object by its id")
    @RequestMapping("/getUserAccountById")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserAccount.class)))
    public UserAccount getUserAccountById(@Parameter(description = "UserAccount ID") Long id, Principal principal) {
        return userAccountRepository.findById(id).orElse(null);
    }

    // Write a method to return all UserAccount objects.
    @Operation(summary = "Get All User Accounts", description = "Returns all UserAccount objects")
    @RequestMapping("/getAllUserAccounts")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserAccount.class, type = "array")))
    public Iterable<UserAccount> getAllUserAccounts(Principal principal) {
        return userAccountRepository.findAll();
    }

    @Operation(summary = "Save User Account", description = "Saves a UserAccount object")
    @RequestMapping("/saveUserAccount")
    @ApiResponse(responseCode = "200", description = "Successful operation", content = @Content(schema = @Schema(implementation = UserAccount.class)))
    public UserAccount saveUserAccount(@Parameter(description = "UserAccount object") UserAccount userAccount, Principal principal) {
        return userAccountRepository.save(userAccount);
    }

    @Operation(summary = "Delete User Account", description = "Deletes a UserAccount object")
    @RequestMapping("/deleteUserAccount")
    @ApiResponse(responseCode = "200", description = "Successful operation")
    public void deleteUserAccount(@Parameter(description = "UserAccount object") UserAccount userAccount, Principal principal) {
        userAccountRepository.delete(userAccount);
    }

}
