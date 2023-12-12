package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Deposit;

@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

}
