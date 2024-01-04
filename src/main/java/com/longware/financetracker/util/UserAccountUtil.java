package com.longware.financetracker.util;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longware.financetracker.entities.UserAccount;
import com.longware.financetracker.service.UserAccountService;

@Component
public class UserAccountUtil {

    private final UserAccountService userAccountService;

    @Autowired
    public UserAccountUtil(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    public UserAccount getUserAccountFromPrincipal(Principal principal) {
        String username = principal.getName(); // Get the username of the authenticated user
        UserAccount userAccount = UserAccount.builder().userName(username).build();

        Optional<UserAccount> userAccountOptional = userAccountService.getEntity(userAccount);

        if (userAccountOptional.isEmpty()) {
            userAccount = userAccountService.save(userAccount);
        } else {
            userAccount = userAccountOptional.get();
        }

        return userAccount;
    }
}