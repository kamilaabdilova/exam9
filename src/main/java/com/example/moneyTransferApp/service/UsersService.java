package com.example.moneyTransferApp.service;

import com.example.moneyTransferApp.model.request.UsersRequest;
import com.example.moneyTransferApp.model.request.ValueRequest;
import com.example.moneyTransferApp.model.response.UsersResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface UsersService {
    UsersResponse create(UsersRequest usersRequest);

    UsersResponse getById(UUID uuid);

    List<UsersResponse> getAll();

    Page<UsersResponse> list(Pageable pageable);

    UsersResponse update(UsersRequest userRequest, UUID uuid);

    void delete(UUID uuid);

    Boolean checkNumber(String number);

    void increaseAccount(String number, ValueRequest value);
}
