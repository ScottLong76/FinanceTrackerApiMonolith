package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.VendorAccount;
import com.longware.financetracker.repository.VendorAccountRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the VendorAccount entity.
 */
@RestController
@RequestMapping("/vendorAccount")
@RequiredArgsConstructor
@Getter
@Setter
public class VendorAccountController {

    private final VendorAccountRepository vendorAccountRepository;

    // Write methods to create, update, and delete VendorAccount objects using
    // available methods in the VendorAccountRepository interface.

    // Write a method to return a VendorAccount object by its id.
    @Operation(summary = "Get VendorAccount by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = VendorAccount.class))),
        @ApiResponse(responseCode = "404", description = "VendorAccount not found")
    })
    @RequestMapping("/getVendorAccountById")
    public VendorAccount getVendorAccountById(@Parameter(description = "VendorAccount ID") Long id) {
        return vendorAccountRepository.findById(id).orElse(null);
    }

    // Write a method to return all VendorAccount objects.
    @Operation(summary = "Get all VendorAccounts")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = VendorAccount.class)))
    })
    @RequestMapping("/getAllVendorAccounts")
    public Iterable<VendorAccount> getAllVendorAccounts(Principal principal) {
        return vendorAccountRepository.findAll();
    }

    @Operation(summary = "Save VendorAccount")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Successful operation", 
            content = @Content(schema = @Schema(implementation = VendorAccount.class)))
    })
    @RequestMapping("/saveVendorAccount")
    public VendorAccount saveVendorAccount(VendorAccount vendorAccount, Principal principal) {
        return vendorAccountRepository.save(vendorAccount);
    }

    @Operation(summary = "Delete VendorAccount")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Successful operation")
    })
    @RequestMapping("/deleteVendorAccount")
    public void deleteVendorAccount(VendorAccount vendorAccount, Principal principal) {
        vendorAccountRepository.delete(vendorAccount);
    }

}
