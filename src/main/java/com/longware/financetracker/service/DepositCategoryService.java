package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.DepositCategory;
import com.longware.financetracker.repository.DepositCategoryRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DepositCategoryService {

    private final DepositCategoryRepository depositCategoryRepository;

    // Write methods to interact with all methods currently defined in the
    // DepositCategoryRepository

    public void deleteAll() {
        depositCategoryRepository.deleteAll();
    }

    public void deleteById(Long id) {
        depositCategoryRepository.deleteById(id);
    }

    public void saveAll(Iterable<DepositCategory> depositCategories) {
        depositCategoryRepository.saveAll(depositCategories);
    }

    public void save(DepositCategory depositCategory) {
        depositCategoryRepository.save(depositCategory);
    }

    public Optional<DepositCategory> findById(Long id) {
        return depositCategoryRepository.findById(id);
    }

    public Iterable<DepositCategory> findAll() {
        return depositCategoryRepository.findAll();
    }

    public boolean entityExists(DepositCategory depositCategory) {
        return depositCategoryRepository.entityExists(depositCategory);
    }

}
