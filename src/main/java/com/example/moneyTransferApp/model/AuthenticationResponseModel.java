package com.example.moneyTransferApp.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthenticationResponseModel {
    UUID id;
    String number;
    String name;
    String phoneNumber;
    BigDecimal account;
    String jwtToken;
    UUID role;
}
