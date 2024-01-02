package com.longware.financetracker.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.longware.financetracker.entities.LoginEvent;
import com.longware.financetracker.repository.LoginEventRepository;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * This class is the REST controller for the LoginEvent entity.
 */
@RestController
@RequestMapping("/loginEvent")
@RequiredArgsConstructor
@Getter
@Setter
public class LoginEventController {

    private final LoginEventRepository loginEventRepository;

    // Write methods to create, update, and delete LoginEvent objects using
    // available methods in the LoginEventRepository interface.

    // Write a method to return a LoginEvent object by its id.
    @RequestMapping("/getLoginEventById")
    public LoginEvent getLoginEventById(Long id, Principal principal) {
        return loginEventRepository.findById(id).orElse(null);
    }

    // Write a method to return all LoginEvent objects.
    @RequestMapping("/getAllLoginEvents")
    public Iterable<LoginEvent> getAllLoginEvents(Principal principal) {
        return loginEventRepository.findAll();
    }

    @RequestMapping("/saveLoginEvent")
    public LoginEvent saveLoginEvent(LoginEvent loginEvent, Principal principal) {
        return loginEventRepository.save(loginEvent);
    }

    @RequestMapping("/deleteLoginEvent")
    public void deleteLoginEvent(LoginEvent loginEvent, Principal principal) {
        loginEventRepository.delete(loginEvent);
    }

}
