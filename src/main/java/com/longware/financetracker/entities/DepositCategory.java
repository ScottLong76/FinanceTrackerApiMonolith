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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@Builder
@AllArgsConstructor
public class DepositCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;

    // bi-directional many-to-one association to Deposit
    @OneToMany(mappedBy = "depositCategory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Deposit> deposits;

    // bi-directional many-to-one association to UserAccount
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserAccount userAccount;
}
