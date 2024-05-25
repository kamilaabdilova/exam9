package com.example.moneyTransferApp.service;


import com.example.moneyTransferApp.entity.Transaction;
import com.example.moneyTransferApp.model.request.ValueRequest;
import com.example.moneyTransferApp.model.response.TransactionResponse;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

public interface TransactionService {
    Page<TransactionResponse> my(Integer page, Integer size, LocalDate from, LocalDate to, UUID typeId);
    void transfer(String number, ValueRequest value);
    void withdraw (ValueRequest value);
    void services (UUID serviceId, ValueRequest value);
}
