package com.example.moneyTransferApp.model.response;

import com.example.moneyTransferApp.entity.Services;
import com.example.moneyTransferApp.entity.TransactionType;
import com.example.moneyTransferApp.entity.Users;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponse {
    UUID id;
    UsersResponse from;
    UsersResponse to;
    ServicesResponce service;
    TransactionTypeResponse type;
    @Schema(type = "string", format = "date")
    @JsonFormat(pattern = "yyyy-MM-dd")
    LocalDate dateTime;
    BigDecimal value;
}
