package com.longware.financetracker.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.repository.DepositCategoryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the DepositCategory entity.
 */
@RestController
@RequestMapping("/depositCategory")
@RequiredArgsConstructor
@Getter
@Setter
public class DepositCategoryController {

    private final DepositCategoryRepository depositCategoryRepository;

    // Write methods to create, update, and delete DepositCategory objects using
    // available methods in the DepositCategoryRepository interface.

    // Write a method to return a DepositCategory object by its id.
    @RequestMapping("/getDepositCategoryById")
    public DepositCategory getDepositCategoryById(Long id) {
        return depositCategoryRepository.findById(id).orElse(null);
    }

    // Write a method to return all DepositCategory objects.
    @RequestMapping("/getAllDepositCategories")
    public Iterable<DepositCategory> getAllDepositCategories() {
        return depositCategoryRepository.findAll();
    }

    @RequestMapping("/saveDepositCategory")
    public DepositCategory saveDepositCategory(DepositCategory depositCategory) {
        return depositCategoryRepository.save(depositCategory);
    }

    @RequestMapping("/deleteDepositCategory")
    public void deleteDepositCategory(DepositCategory depositCategory) {
        depositCategoryRepository.delete(depositCategory);
    }

}
