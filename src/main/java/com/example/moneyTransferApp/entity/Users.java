package com.example.moneyTransferApp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.GenericGenerator;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "org.hibernate.id.UUIDGenerator")
    UUID id;
    @Column(columnDefinition = "TEXT")
    String name;
    @Column(nullable = false, unique = true, columnDefinition = "TEXT")
    String password;
    @Column(length = 55)
    String phoneNumber;
    @Column(length = 6, unique = true)
    String number;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn
    Role role;
    BigDecimal account;
}
