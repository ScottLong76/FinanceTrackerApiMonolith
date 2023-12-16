package com.longware.financetracker.repository;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BudgetEntry;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BudgetEntryRepository extends EntityRepositoryInterface<BudgetEntry, Long> {

    public boolean existsByStartDateAndFrequencyAndNoteAndScheduledTypeAndScheduledAmountAndUserAccount(Date startDate,
            String frequency, String note, String scheduledType, double scheduledAmount, UserAccount userAccount);

    public default boolean entityExists(BudgetEntry budgetEntry) {
        return existsByStartDateAndFrequencyAndNoteAndScheduledTypeAndScheduledAmountAndUserAccount(
                budgetEntry.getStartDate(), budgetEntry.getFrequency(), budgetEntry.getNote(),
                budgetEntry.getScheduledType(), budgetEntry.getScheduledAmount(), budgetEntry.getUserAccount());
    }
}
