package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Bill;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.BillRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

// Service Layer for Bill Entity
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class BillService {
    // Write methods to interact with all methods currently defined in the
    // BillRepository

    private final BillRepository billRepository;

    public void deleteAll() {
        billRepository.deleteAll();
    }

    public void deleteById(Long id) {
        billRepository.deleteById(id);
    }

    public void delete(Bill bill) {
        billRepository.delete(bill);
    }

    public void saveAll(Iterable<Bill> bills) {
        billRepository.saveAll(bills);
    }

    public Bill save(Bill bill) {
        return billRepository.save(bill);
    }

    public Optional<Bill> findById(Long id) {
        return billRepository.findById(id);
    }

    public Iterable<Bill> findAll() {
        return billRepository.findAll();
    }

    public Iterable<Bill> findAllById(Iterable<Long> ids) {
        return billRepository.findAllById(ids);
    }

    public long count() {
        return billRepository.count();
    }

    public boolean existsById(Long id) {
        return billRepository.existsById(id);
    }

    public Optional<Bill> getEntity(Bill bill) {
        return billRepository.getEntity(bill);
    }

    public Iterable<Bill> findAllByUserAccount(UserAccount userAccount) {
        return billRepository.findAllByUserAccount(userAccount);
    }

    public Page<Bill> findAllByUserAccount(UserAccount userAccount, Pageable pageable) {
        return billRepository.findPageByUserAccount(userAccount, pageable);
    }

}
