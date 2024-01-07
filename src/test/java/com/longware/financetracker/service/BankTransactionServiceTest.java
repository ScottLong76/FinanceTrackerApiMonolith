package com.longware.financetracker.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.longware.financetracker.entities.BankTransaction;
import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.BankTransactionRepository;

class BankTransactionServiceTest {

    @Mock
    private BankTransactionRepository bankTransactionRepository;

    @InjectMocks
    private BankTransactionService bankTransactionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSave() {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setId(1L);
        bankTransaction.setDescription("Test Transaction");

        // Save the bank transaction
        BankTransaction savedTransaction = bankTransactionService.save(bankTransaction);

        assertNotNull(savedTransaction);
        assertEquals(bankTransaction.getId(), savedTransaction.getId());
        assertEquals(bankTransaction.getDescription(), savedTransaction.getDescription());
    }

    @Test
    void testFindById() {
        Long transactionId = 1L;

        // Mock the repository to return a bank transaction with the given ID
        BankTransaction mockTransaction = new BankTransaction();
        mockTransaction.setId(transactionId);
        mockTransaction.setDescription("Mock Transaction");
        Optional<BankTransaction> optionalTransaction = Optional.of(mockTransaction);
        when(bankTransactionRepository.findById(transactionId)).thenReturn(optionalTransaction);

        // Find the bank transaction by ID
        Optional<BankTransaction> foundTransaction = bankTransactionService.findById(transactionId);

        assertTrue(foundTransaction.isPresent());
        assertEquals(transactionId, foundTransaction.get().getId());
        assertEquals("Mock Transaction", foundTransaction.get().getDescription());
    }

    @Test
    void testFindByUserAccount() {
        UserAccount userAccount = new UserAccount();
        userAccount.setId(1L);

        // Mock the repository to return a list of bank transactions for the given user
        // account
        List<BankTransaction> mockTransactions = new ArrayList<>();
        mockTransactions.add(new BankTransaction());
        mockTransactions.add(new BankTransaction());
        when(bankTransactionRepository.findAllByUserAccount(userAccount)).thenReturn(mockTransactions);

        // Find the bank transactions by user account
        Iterable<BankTransaction> foundTransactions = bankTransactionService.findByUserAccount(userAccount);

        assertNotNull(foundTransactions);
        assertEquals(2, ((List<BankTransaction>) foundTransactions).size());
    }

    // Add more test methods for other methods in the BankTransactionService class

}
