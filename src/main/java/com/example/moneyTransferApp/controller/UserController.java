package com.example.moneyTransferApp.controller;

import com.example.moneyTransferApp.model.request.UsersRequest;
import com.example.moneyTransferApp.model.request.ValueRequest;
import com.example.moneyTransferApp.model.response.UsersResponse;
import com.example.moneyTransferApp.service.UsersService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/user")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
public class UserController {
    UsersService usersService;

    @Autowired
    public UserController(UsersService usersService) {
        this.usersService = usersService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsersResponse> createUser(@RequestBody @Valid UsersRequest usersRequest) {
        UsersResponse saveUser = usersService.create(usersRequest);
        return new ResponseEntity<>(saveUser, HttpStatus.CREATED);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UsersResponse> getUserById(@PathVariable UUID userId) {
        UsersResponse getById = usersService.getById(userId);
        return new ResponseEntity<>(getById, HttpStatus.OK);
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UsersResponse>> getAllUsers() {
        List<UsersResponse> allUsers = usersService.getAll();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<Page<UsersResponse>> getUsersPaged(Pageable pageable) {
        Page<UsersResponse> userPage = usersService.list(pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UsersResponse> updateUser(@RequestBody UsersRequest userRequest, @PathVariable UUID userId) {
        UsersResponse updateUser = usersService.update(userRequest, userId);
        return new ResponseEntity<>(updateUser, HttpStatus.CREATED);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID userId) {
        usersService.delete(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/check/{number}")
    public Boolean checkNumber(@PathVariable String number){
        return usersService.checkNumber(number);
    }

    @PostMapping("/increase/{number}")
    public void increaseAccount(@PathVariable String number, @RequestBody ValueRequest value){
        usersService.increaseAccount(number, value);
    }
}
