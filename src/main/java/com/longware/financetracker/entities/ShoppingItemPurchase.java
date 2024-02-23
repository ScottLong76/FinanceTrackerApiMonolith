package com.longware.financetracker.entities;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.PostLoad;
import javax.persistence.Transient;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

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