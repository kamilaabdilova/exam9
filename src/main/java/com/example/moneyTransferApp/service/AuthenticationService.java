package com.example.moneyTransferApp.service;

import com.example.moneyTransferApp.model.AuthenticationRequestModel;
import com.example.moneyTransferApp.model.AuthenticationResponseModel;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
    AuthenticationResponseModel generateToken(AuthenticationRequestModel authRequest, HttpServletResponse response);
}
