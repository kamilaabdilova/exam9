package com.example.moneyTransferApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table
public class Transaction {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="from_id")
    Users from;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="to_id")
    Users to;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    Services service;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn
    TransactionType type;
    @Column(nullable = false)
    LocalDate dateTime;
    @Column(nullable = false)
    BigDecimal value;
}
