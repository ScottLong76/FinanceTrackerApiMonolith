package com.longware.financetracker.repository;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.Bill;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BillRepository extends EntityRepositoryInterface<Bill, Long> {

    public boolean existsByUserAccountAndFixedPaymentAmountAndFixedPaymentsAndPaymentSchedule(UserAccount userAccount,
            Float fixedPaymentAmount, String fixedPayments, String paymentSchedule);

    public default boolean entityExists(Bill bill) {
        return existsByUserAccountAndFixedPaymentAmountAndFixedPaymentsAndPaymentSchedule(bill.getUserAccount(),
                bill.getFixedPaymentAmount(), bill.getFixedPayments(), bill.getPaymentSchedule());
    }
}
