package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

}
