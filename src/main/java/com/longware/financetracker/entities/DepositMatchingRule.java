package com.longware.financetracker.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@JsonIgnoreProperties({"userAccount"})
public class DepositMatchingRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer executionOrder;

    private String depositLikeCriteria;

    private String depositRegexCriteria;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private UserAccount userAccount;

    @ManyToOne
    @JoinColumn(name = "deposit_id")
    private Deposit deposit;
}