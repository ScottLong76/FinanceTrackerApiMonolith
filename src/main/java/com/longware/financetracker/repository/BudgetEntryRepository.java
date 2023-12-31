package com.longware.financetracker.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.BudgetEntry;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.interfaces.EntityRepositoryInterface;

@Repository
public interface BudgetEntryRepository extends EntityRepositoryInterface<BudgetEntry, Long> {

        public Iterable<BudgetEntry> findAllByUserAccount(UserAccount userAccount);

        public boolean existsByStartDateAndFrequencyAndNoteAndScheduledTypeAndScheduledAmountAndUserAccount(
                        Date startDate,
                        String frequency, String note, String scheduledType, double scheduledAmount,
                        UserAccount userAccount);

        public Optional<BudgetEntry> findByStartDateAndFrequencyAndNoteAndScheduledTypeAndScheduledAmountAndUserAccount(
                        Date startDate, String frequency, String note, String scheduledType, double scheduledAmount,
                        UserAccount userAccount);

        public default boolean entityExists(BudgetEntry budgetEntry) {
                return existsByStartDateAndFrequencyAndNoteAndScheduledTypeAndScheduledAmountAndUserAccount(
                                budgetEntry.getStartDate(), budgetEntry.getFrequency(), budgetEntry.getNote(),
                                budgetEntry.getScheduledType(), budgetEntry.getScheduledAmount(),
                                budgetEntry.getUserAccount());
        }

        public default Optional<BudgetEntry> getEntity(BudgetEntry budgetEntry) {
                return findByStartDateAndFrequencyAndNoteAndScheduledTypeAndScheduledAmountAndUserAccount(
                                budgetEntry.getStartDate(), budgetEntry.getFrequency(), budgetEntry.getNote(),
                                budgetEntry.getScheduledType(), budgetEntry.getScheduledAmount(),
                                budgetEntry.getUserAccount());
        }
}
