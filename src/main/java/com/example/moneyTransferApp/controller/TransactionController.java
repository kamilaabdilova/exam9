package com.example.moneyTransferApp.controller;

import com.example.moneyTransferApp.model.request.ValueRequest;
import com.example.moneyTransferApp.model.response.ProviderResponse;
import com.example.moneyTransferApp.model.response.ServicesResponce;
import com.example.moneyTransferApp.model.response.TransactionResponse;
import com.example.moneyTransferApp.service.ServicesService;
import com.example.moneyTransferApp.service.TransactionService;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequiredArgsConstructor
public class TransactionController {
    TransactionService transactionService;

    @GetMapping("/my")
    public Page<TransactionResponse> my(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size,
            @RequestParam(required = false) @Schema(type = "string", format = "date") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate from,
            @RequestParam(required = false) @Schema(type = "string", format = "date") @JsonFormat(pattern = "yyyy-MM-dd") LocalDate to,
            @RequestParam(required = false) UUID typeId) {
        return transactionService.my(page, size, from, to, typeId);
    }

    @PostMapping("/transfer/{number}")
    public void transfer(@PathVariable String number, @RequestBody ValueRequest value) {
        transactionService.transfer(number, value);
    }

    @PostMapping("/withdraw")
    public void withdraw(@RequestBody ValueRequest value) {
        transactionService.withdraw(value);
    }

    @PostMapping("/services/{serviceId}")
    public void services(@PathVariable UUID serviceId, @RequestBody ValueRequest value) {
        transactionService.services(serviceId, value);
    }
}
