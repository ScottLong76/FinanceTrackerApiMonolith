package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.ShoppingItemPurchase;

@Repository
public interface ShoppingItemPurchaseRepository extends JpaRepository<ShoppingItemPurchase, Long> {

}