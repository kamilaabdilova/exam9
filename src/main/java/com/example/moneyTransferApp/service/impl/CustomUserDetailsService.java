package com.example.moneyTransferApp.service.impl;

import com.example.moneyTransferApp.configuration.CustomUserDetails;
import com.example.moneyTransferApp.entity.Users;
import com.example.moneyTransferApp.repository.UsersRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class CustomUserDetailsService implements UserDetailsService {
    UsersRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user = userRepository.findByNumber(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new CustomUserDetails(user, grantedAuthorities);
    }
}