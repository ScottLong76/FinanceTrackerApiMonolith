package com.longware.financetracker.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PostLoad;
import jakarta.persistence.Transient;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ShoppingItemPurchase {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private Float amount;
        private Integer quantity;
        private Float totalAmount;

        @PersistenceContext
        @Transient
        private EntityManager entityManager;

        @Transient
        private Integer totalItemsForTransaction;

        @Transient
        private Float totalAmountForTransaction;

        @PostLoad
        private void calculateTotalItemsAndAmount() {
                CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
                CriteriaQuery<Long> countQuery = criteriaBuilder.createQuery(Long.class);
                Root<ShoppingItemPurchase> root = countQuery.from(ShoppingItemPurchase.class);
                countQuery.select(criteriaBuilder.count(root.get("item_purchase_amount")))
                                .where(criteriaBuilder.equal(root.get("bankTransaction"), this.getBankTransaction()));
                totalItemsForTransaction = entityManager.createQuery(countQuery).getSingleResult().intValue();

                CriteriaQuery<Float> sumQuery = criteriaBuilder.createQuery(Float.class);
                root = sumQuery.from(ShoppingItemPurchase.class);
                Expression<Float> product = criteriaBuilder.prod(root.get("item_purchase_amount"),
                                root.get("item_purchase_quantity"));
                sumQuery.select(criteriaBuilder.sum(product))
                                .where(criteriaBuilder.equal(root.get("bankTransaction"), this.getBankTransaction()));
                totalAmountForTransaction = entityManager.createQuery(sumQuery).getSingleResult();
        }

        @ManyToOne
        @JoinColumn(name = "shopping_item_id")
        private ShoppingItem shoppingItem;

        @ManyToOne
        @JoinColumn(name = "bank_transaction_id")
        private BankTransaction bankTransaction;

        @ManyToOne
        @JoinColumn(name = "user_id")
        private UserAccount userAccount;

}