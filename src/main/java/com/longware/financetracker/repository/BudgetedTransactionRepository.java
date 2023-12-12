package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BudgetedTransaction;

@Repository
public interface BudgetedTransactionRepository extends JpaRepository<BudgetedTransaction, Long> {

}
