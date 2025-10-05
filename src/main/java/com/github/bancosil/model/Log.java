package com.github.bancosil.model;

import com.github.bancosil.service.operation.OperationType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Log {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private OperationType operationType;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Account author;

    @ManyToOne
    @JoinColumn(name = "receiver_id")
    private Account receiver;

    private BigDecimal amount;

    private LocalDateTime createdAt;

    public Log(OperationType operationType, Account author, BigDecimal amount){
        this(operationType, author, null, amount);
    }

    public Log(OperationType operationType, Account sender, Account receiver, BigDecimal amount) {
        this.operationType = operationType;
        this.author = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.createdAt = LocalDateTime.now();
    }

    public String getAuthorName() {
        return author.getUsername();
    }

    public String getReceiverName(){
        return receiver == null ? null : receiver.getUsername();
    }
}
