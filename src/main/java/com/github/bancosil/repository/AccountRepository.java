package com.github.bancosil.repository;

import com.github.bancosil.model.Account;
import com.github.bancosil.model.valueobjects.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.username.username = :username")
    Optional<Account> findByUsername(String username);
}
