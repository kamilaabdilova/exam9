package com.example.moneyTransferApp.service.impl;

import com.example.moneyTransferApp.configuration.CustomUserDetails;
import com.example.moneyTransferApp.entity.Users;
import com.example.moneyTransferApp.exception.CustomError;
import com.example.moneyTransferApp.exception.CustomException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@RequiredArgsConstructor
public class CurrentUserServiceImpl {
    public Users getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated() || !(authentication.getPrincipal() instanceof CustomUserDetails userDetails)) {
            throw new CustomException(CustomError.AUTHENTICATION_FAILED, log);
        }
        return userDetails.getUser();
    }
}
