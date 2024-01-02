package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.Bill;
import com.longware.financetracker.repository.BillRepository;

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

    private final BillRepository billRepository;

    // Write methods to create, update, and delete Bill objects using available
    // methods in the BillRepository interface.

    // Write a method to return a Bill object by its id.
    @RequestMapping("/getBillById")
    public Bill getBillById(Long id, Principal principal) {
        return billRepository.findById(id).orElse(null);
    }

    // Write a method to return all Bill objects.
    @RequestMapping("/getAllBills")
    public Iterable<Bill> getAllBills(Principal principal) {
        return billRepository.findAll();
    }

    @RequestMapping("/saveBill")
    public Bill saveBill(Bill bill, Principal principal) {
        return billRepository.save(bill);
    }

    @RequestMapping("/deleteBill")
    public void deleteBill(Bill bill, Principal principal) {
        billRepository.delete(bill);
    }

}
