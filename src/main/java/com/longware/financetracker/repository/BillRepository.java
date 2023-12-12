package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Bill;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {

}
