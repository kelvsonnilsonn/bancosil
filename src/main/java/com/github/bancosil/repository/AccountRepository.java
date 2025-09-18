package com.github.bancosil.repository;

import com.github.bancosil.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.username.username = :username AND a.password.password =:password")
    Optional<Account> findByUsernameAndPassword(@Param("username") String username, @Param("password") String password);

    @Query("SELECT a FROM Account a WHERE a.username.username LIKE CONCAT (:username, '%')")
    List<Account> findByUsername(@Param("username") String username);
}
