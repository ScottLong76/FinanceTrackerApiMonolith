package com.longware.financetracker.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@NoArgsConstructor
public class BudgetedTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean paid;
    private float transactionAmount;
    private Date dueDate;

    @ManyToOne
    @JoinColumn(name = "budget_entry_id")
    private BudgetEntry budgetEntry;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
}