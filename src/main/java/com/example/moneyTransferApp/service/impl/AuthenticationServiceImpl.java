package com.example.moneyTransferApp.service.impl;

import com.example.moneyTransferApp.configuration.JwtTokenUtils;
import com.example.moneyTransferApp.entity.Users;
import com.example.moneyTransferApp.exception.CustomError;
import com.example.moneyTransferApp.exception.CustomException;
import com.example.moneyTransferApp.model.AuthenticationRequestModel;
import com.example.moneyTransferApp.model.AuthenticationResponseModel;
import com.example.moneyTransferApp.repository.UsersRepository;
import com.example.moneyTransferApp.service.AuthenticationService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthenticationServiceImpl implements AuthenticationService {
    UsersRepository usersRepository;
    AuthenticationManager authenticationManager;
    JwtTokenUtils jwtUtil;

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponseModel generateToken(AuthenticationRequestModel authRequest, HttpServletResponse response) {
        Users user = usersRepository.findByNumber(authRequest.getUsername())
                .orElseThrow(() -> new CustomException(CustomError.USER_NOT_FOUND, log));

        String jwt = null;
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getNumber(), authRequest.getPassword()));
            jwt = jwtUtil.generateToken(user.getNumber());
        } catch (Exception ex) {
            log.error(ex.getLocalizedMessage());
            throw new CustomException(CustomError.NOT_AUTHORIZED, log);
        }

        log.info("Пользователь с username = " + authRequest.getUsername() + " авторизован");
        return AuthenticationResponseModel.builder()
                .jwtToken(jwt)
                .id(user.getId())
                .number(user.getNumber())
                .name(user.getName())
                .phoneNumber(user.getPhoneNumber())
                .account(user.getAccount())
                .role(user.getRole().getId())
                .build();
    }
}
