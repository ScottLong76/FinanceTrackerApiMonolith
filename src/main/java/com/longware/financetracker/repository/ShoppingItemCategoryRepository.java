package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ShoppingItemCategory;

@Repository
public interface ShoppingItemCategoryRepository extends JpaRepository<ShoppingItemCategory, Long> {

}
