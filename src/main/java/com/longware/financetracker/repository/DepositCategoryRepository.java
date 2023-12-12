package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.DepositCategory;

@Repository
public interface DepositCategoryRepository extends JpaRepository<DepositCategory, Long> {

}
