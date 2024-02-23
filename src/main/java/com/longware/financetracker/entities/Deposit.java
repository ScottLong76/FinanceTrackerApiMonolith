package com.longware.financetracker.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Builder
@Entity
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
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