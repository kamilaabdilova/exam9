package com.example.moneyTransferApp.model.request;

import com.example.moneyTransferApp.model.response.ServicesResponce;
import com.example.moneyTransferApp.model.response.TransactionTypeResponse;
import com.example.moneyTransferApp.model.response.UsersResponse;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequest {
    UUID toId;
    UUID serviceId;
    UUID typeId;
    Date dateTime;
    BigDecimal value;
}
