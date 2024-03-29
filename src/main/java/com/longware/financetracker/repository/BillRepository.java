package com.longware.financetracker.repository;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Bill;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BillRepository extends EntityRepositoryInterface<Bill, Long> {

        public Iterable<Bill> findAllByUserAccount(UserAccount userAccount);

        public boolean existsByUserAccountAndFixedPaymentAmountAndFixedPaymentsAndPaymentSchedule(
                        UserAccount userAccount,
                        Float fixedPaymentAmount, String fixedPayments, String paymentSchedule);

        public Optional<Bill> findByUserAccountAndFixedPaymentAmountAndFixedPaymentsAndPaymentSchedule(
                        UserAccount userAccount, Float fixedPaymentAmount, String fixedPayments,
                        String paymentSchedule);

        public default boolean entityExists(Bill bill) {
                return existsByUserAccountAndFixedPaymentAmountAndFixedPaymentsAndPaymentSchedule(bill.getUserAccount(),
                                bill.getFixedPaymentAmount(), bill.getFixedPayments(), bill.getPaymentSchedule());
        }

        public default Optional<Bill> getEntity(Bill bill) {
                return findByUserAccountAndFixedPaymentAmountAndFixedPaymentsAndPaymentSchedule(bill.getUserAccount(),
                                bill.getFixedPaymentAmount(), bill.getFixedPayments(), bill.getPaymentSchedule());
        }

        
}
