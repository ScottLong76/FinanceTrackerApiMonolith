package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.Deposit;
import com.longware.financetracker.repository.DepositRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to handle all the business logic for the Deposit entity.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class DepositService {

    private final DepositRepository depositRepository;

    // Write methods to interact with all methods currently defined in the
    // DepositRepository

    public void deleteAll() {
        depositRepository.deleteAll();
    }

    public void deleteById(Long id) {
        depositRepository.deleteById(id);
    }

    public void saveAll(Iterable<Deposit> deposits) {
        depositRepository.saveAll(deposits);
    }

    public Deposit save(Deposit deposit) {
        return depositRepository.save(deposit);
    }

    public Optional<Deposit> findById(Long id) {
        return depositRepository.findById(id);
    }

    public Iterable<Deposit> findAll() {
        return depositRepository.findAll();
    }

    public boolean entityExists(Deposit deposit) {
        return depositRepository.entityExists(deposit);
    }

    public Optional<Deposit> getEntity(Deposit deposit) {
        return depositRepository.getEntity(deposit);
    }

}
