package com.longware.financetracker.entities;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @ManyToOne
    @JoinColumn(name = "shopping_item_category_id")
    private ShoppingItemCategory shoppingItemCategory;

    @OneToMany(mappedBy = "shoppingItem", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ShoppingItemPurchase> shoppingItemPurchases;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

}