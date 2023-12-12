package com.longware.financetracker.service;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Bill;
import com.longware.financetracker.repository.BillRepository;

import java.util.Optional;

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

    public void saveAll(Iterable<Bill> bills) {
        billRepository.saveAll(bills);
    }

    public void save(Bill bill) {
        billRepository.save(bill);
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

}
