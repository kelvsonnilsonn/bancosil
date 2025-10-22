package com.github.bancosil.event;

import io.hypersistence.utils.hibernate.type.json.JsonType;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name="event_store")
@Getter
public class EventStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String aggregateType;

    private Long accountId;

    private String eventType;

    @Type(JsonType.class)
    @Column(columnDefinition = "JSON")
    private Object eventData;

    private LocalDateTime createdAt;

    public EventStore(String aggregateType, Long accountId, String eventType, Object eventData){
        this.aggregateType = aggregateType;
        this.accountId = accountId;
        this.eventType = eventType;
        this.eventData = eventData;
        this.createdAt = LocalDateTime.now();
    }
}
