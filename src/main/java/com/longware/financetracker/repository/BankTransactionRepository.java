package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BankTransaction;

@Repository
public interface BankTransactionRepository extends JpaRepository<BankTransaction, Long> {

}
