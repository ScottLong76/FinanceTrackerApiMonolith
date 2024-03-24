package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Bill;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.BillService;
import com.longware.financetracker.util.UserAccountUtil;

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
 * This class is the REST controller for the Bill entity.
 */
@RestController
@RequestMapping("/bill")
@RequiredArgsConstructor
@Getter
@Setter
public class BillController {

    private final BillService billService;
    private final UserAccountUtil userAccountUtil;

    // Write methods to create, update, and delete Bill objects using available
    // methods in the BillRepository interface.

    // Write a method to return a Bill object by its id.
    @Operation(summary = "Get a Bill by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the Bill", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Bill.class)) }),
        @ApiResponse(responseCode = "404", description = "Bill not found") })
    @RequestMapping("/getBillById")
    public Bill getBillById(@Parameter(description = "ID of the Bill") Long id, Principal principal) {
        return billService.findById(id).orElse(null);
    }

    // Write a method to return all Bill objects.
    @Operation(summary = "Get all Bills")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found all Bills", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Bill.class)) }) })
    @RequestMapping("/getAllBills")
    public Page<Bill> getAllBills(Principal principal ,
                    @Parameter(description = "Page number") @RequestParam(defaultValue = "0") int page,
            @Parameter(description = "Number of items per page") @RequestParam(defaultValue = "10") int size,
            @Parameter(description = "Sort by") @RequestParam(defaultValue = "id") String sortBy) {
                UserAccount userAccount = userAccountUtil.getUserAccountFromPrincipal(principal);
        return billService.findAllByUserAccount(userAccount, PageRequest.of(page, size, Sort.by(sortBy)));
    }

    @Operation(summary = "Save a Bill")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bill saved", 
            content = { @Content(mediaType = "application/json", 
                schema = @Schema(implementation = Bill.class)) }) })
    @RequestMapping("/saveBill")
    public Bill saveBill(@Parameter(description = "Bill object to be saved") Bill bill, Principal principal) {
        return billService.save(bill);
    }

    @Operation(summary = "Delete a Bill")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Bill deleted") })
    @RequestMapping("/deleteBill")
    public void deleteBill(@Parameter(description = "Bill object to be deleted") Bill bill, Principal principal) {
        billService.delete(bill);
    }

}
