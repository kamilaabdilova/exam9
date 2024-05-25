package com.example.moneyTransferApp.mapper;


import com.example.moneyTransferApp.entity.Role;
import com.example.moneyTransferApp.entity.Services;
import com.example.moneyTransferApp.entity.TransactionType;
import com.example.moneyTransferApp.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface DefaultMapper {
    @Named("setRole")
    default Role setRole(UUID id) {
        if (id == null) {
            return null;
        }
        return Role.builder().id(id).build();
    }

    @Named("setUser")
    default Users setUser(UUID id) {
        if (id == null) {
            return null;
        }
        return Users.builder().id(id).build();
    }
    @Named("setServices")
    default Services setServices(UUID id) {
        if (id == null) {
            return null;
        }
        return Services.builder().id(id).build();
    }
    @Named("setTransactionType")
    default TransactionType setTransactionType(UUID id) {
        if (id == null) {
            return null;
        }
        return TransactionType.builder().id(id).build();
    }
}
