package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Bank;

// Make me a Spring Data JPA repository for the Bank entity.
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

}
