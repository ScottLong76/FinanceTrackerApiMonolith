package com.longware.financetracker.entities;

import java.util.Date;

import javax.persistence.Entity; // Fix import
import javax.persistence.GeneratedValue; // Fix import
import javax.persistence.GenerationType; // Fix import
import javax.persistence.Id; // Fix import
import javax.persistence.JoinColumn; // Fix import
import javax.persistence.ManyToOne; // Fix import

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@NoArgsConstructor
public class BudgetEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date startDate;
    private String frequency;
    private String note;
    private String scheduledType;
    private Date endDate;
    private Float scheduledAmount;

    @ManyToOne
    @JoinColumn(name = "bill_id")
    private Bill bill;

    @ManyToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
}