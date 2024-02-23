package com.longware.financetracker.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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