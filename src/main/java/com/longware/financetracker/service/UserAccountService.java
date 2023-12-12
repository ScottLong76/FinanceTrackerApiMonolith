package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.repository.UserAccountRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to handle the business logic for the user account.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class UserAccountService {

    private final UserAccountRepository userAccountRepository;

    // Write methods to interact with all methods currently defined in the
    // UserAccountRepository

    public void deleteAll() {
        userAccountRepository.deleteAll();
    }

    public void deleteById(Long id) {
        userAccountRepository.deleteById(id);
    }

    public void saveAll(Iterable<UserAccount> userAccounts) {
        userAccountRepository.saveAll(userAccounts);
    }

    public void save(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
    }

    public Iterable<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    public Optional<UserAccount> findById(Long id) {
        return userAccountRepository.findById(id);
    }

}
