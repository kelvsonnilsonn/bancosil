package com.github.bancosil.repository;

import com.github.bancosil.event.EventStore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface EventStoreRepository extends JpaRepository<EventStore, Long> {

    Page<EventStore> findByAggregateType(Pageable pageable, String aggregateType);

    Page<EventStore> findByAggregateId(Pageable pageable, Long aggregateId);

    @Query(value = "SELECT * FROM event_store WHERE JSON_EXTRACT(event_data, '$.userId') = :userId", nativeQuery = true)
    Page<EventStore> findAllEventsByUserId(Pageable pageable, Long userId);

    @Query(value = "SELECT * FROM event_store WHERE JSON_EXTRACT(event_data, '$.userId') = :userId AND created_at >= :startDate", nativeQuery = true)
    Page<EventStore> findEventsAfterDateWithUserId(Pageable pageable, LocalDateTime startDate, Long userId);

    @Query(value = "SELECT * FROM event_Store WHERE JSON_EXTRACT(event_data, '$.userId') = :userId AND created_at < :date", nativeQuery = true)
    Page<EventStore> findEventsBeforeDateWithUserId(Pageable pageable, LocalDateTime date, Long userId);

    @Query(value = "SELECT * FROM event_Store WHERE JSON_EXTRACT(event_data, '$.userId') = :userId AND created_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    Page<EventStore> findEventsInDateIntervalWithUserId(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate, Long userId);

    @Query(value ="SELECT * FROM event_store WHERE created_at >= :startDate", nativeQuery = true)
    Page<EventStore> findEventsAfterDate(Pageable pageable, LocalDateTime startDate);

    @Query(value = "SELECT * FROM event_store WHERE created_at < :date", nativeQuery = true)
    Page<EventStore> findEventsBeforeDate(Pageable pageable, LocalDateTime date);

    @Query(value = "SELECT * FROM event_store WHERE created_at BETWEEN :startDate AND :endDate", nativeQuery = true)
    Page<EventStore> findEventsInDateInterval(Pageable pageable, LocalDateTime startDate, LocalDateTime endDate);
}