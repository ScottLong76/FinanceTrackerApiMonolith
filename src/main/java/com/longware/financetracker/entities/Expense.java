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
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@Builder
@RequiredArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL)
    private List<Bill> bills;

    @ManyToOne
    @JoinColumn(name = "expense_category_id")
    private ExpenseCategory expenseCategory;

    @OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Vendor> vendors;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;

    public Expense(Long id, String description, List<Bill> bills, ExpenseCategory expenseCategory, List<Vendor> vendors, UserAccount userAccount) {
        this.id = id;
        this.description = description;
        this.bills = bills;
        this.expenseCategory = expenseCategory;
        this.vendors = vendors;
        this.userAccount = userAccount;
    }
}

