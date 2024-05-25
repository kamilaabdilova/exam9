package com.example.moneyTransferApp.controller;

import com.example.moneyTransferApp.model.AuthenticationRequestModel;
import com.example.moneyTransferApp.model.AuthenticationResponseModel;
import com.example.moneyTransferApp.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public AuthenticationResponseModel generateToken(@Valid @RequestBody AuthenticationRequestModel authRequest, HttpServletResponse response) {
        return authenticationService.generateToken(authRequest, response);
    }
}
