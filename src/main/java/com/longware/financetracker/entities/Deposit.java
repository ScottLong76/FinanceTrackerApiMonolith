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

@Entity
@Data
public class Deposit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BankTransaction> bankTransactions;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<DepositMatchingRule> depositMatchingRules;

    @OneToMany(mappedBy = "deposit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BudgetEntry> budgetEntries;

    @ManyToOne
    @JoinColumn(name = "deposit_category_id")
    private DepositCategory depositCategory;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
}