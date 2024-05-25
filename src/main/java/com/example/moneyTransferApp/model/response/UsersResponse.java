package com.example.moneyTransferApp.model.response;

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
public class UsersResponse {
    UUID id;
    String number;
    String name;
    String phoneNumber;
    BigDecimal account;
    RoleResponse role;
}
