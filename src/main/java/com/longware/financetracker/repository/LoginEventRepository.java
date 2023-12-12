package com.longware.financetracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.longware.financetracker.entities.LoginEvent;

@Repository
public interface LoginEventRepository extends JpaRepository<LoginEvent, Long> {

}
