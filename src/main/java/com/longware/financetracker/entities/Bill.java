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

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@NoArgsConstructor
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Float fixedPaymentAmount;
    private String fixedPayments;
    private String paymentSchedule;

    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    @OneToMany(mappedBy = "bill", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<BudgetEntry> budgetEntries;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
}