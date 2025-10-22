package com.github.bancosil.repository;

import com.github.bancosil.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query(value = "SELECT * FROM accounts WHERE username =:username",nativeQuery = true)
    Optional<Account> findByUsername(String username);
}
