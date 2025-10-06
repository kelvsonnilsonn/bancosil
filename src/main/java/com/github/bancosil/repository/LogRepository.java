package com.github.bancosil.repository;

import com.github.bancosil.model.Log;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface LogRepository extends JpaRepository<Log, Long> {

    @Query("SELECT l FROM Log l WHERE l.author.id = :authorId")
    Page<Log> findByAuthorId(Pageable pageable, Long authorId);

    @Query("SELECT l FROM Log l WHERE l.createdAt BETWEEN :startDate AND :endDate")
    Page<Log>  findByInterval(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);
}
