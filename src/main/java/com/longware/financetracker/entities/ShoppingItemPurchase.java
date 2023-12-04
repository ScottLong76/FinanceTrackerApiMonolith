package com.longware.financetracker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ShoppingItemPurchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;
    private Integer quantity;
    private Float totalAmount;

    // TODO: Figure out the modern way of doing these formulas - must be DB agnostic
    // @Formula("(select count(s.item_purchase_amount) from shoppingItemPurchase s
    // where s.bank_transaction_id = bank_transaction_id)")
    // private Integer totalItemsForTransaction;

    // @Formula("(select sum(s.item_purchase_amount * s.item_purchase_quantity) from
    // shoppingItemPurchase s where s.bank_transaction_id = bank_transaction_id)")
    // private Float totalAmountForTransaction;

    @ManyToOne
    @JoinColumn(name = "shopping_item_id")
    private ShoppingItem shoppingItem;

    @ManyToOne
    @JoinColumn(name = "bank_transaction_id")
    private BankTransaction bankTransaction;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

}