package com.longware.financetracker.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.longware.financetracker.entities.LoginEvent;
import com.longware.financetracker.repository.LoginEventRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is used to handle login events.
 */
@Service
@RequiredArgsConstructor
@Getter
@Setter
public class LoginEventService {

    private final LoginEventRepository loginEventRepository;

    // Write methods to interact with all methods currently defined in the
    // LoginEventRepository

    public void deleteAll() {
        loginEventRepository.deleteAll();
    }

    public void deleteById(Long id) {
        loginEventRepository.deleteById(id);
    }

    public void saveAll(Iterable<LoginEvent> loginEvents) {
        loginEventRepository.saveAll(loginEvents);
    }

    public LoginEvent save(LoginEvent loginEvent) {
        return loginEventRepository.save(loginEvent);
    }

    public Optional<LoginEvent> findById(Long id) {
        return loginEventRepository.findById(id);
    }

}
