package com.example.moneyTransferApp.mapper;

import com.example.moneyTransferApp.entity.Transaction;
import com.example.moneyTransferApp.entity.TransactionType;
import com.example.moneyTransferApp.entity.Users;
import com.example.moneyTransferApp.model.request.TransactionRequest;
import com.example.moneyTransferApp.model.request.UsersRequest;
import com.example.moneyTransferApp.model.response.TransactionResponse;
import com.example.moneyTransferApp.model.response.TransactionTypeResponse;
import com.example.moneyTransferApp.model.response.UsersResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                DefaultMapper.class,
                UserMapper.class,
                ServicesMapper.class
        }
)
public interface TransactionMapper {
    @Mapping(target = "to", source = "toId", qualifiedByName = "setUser")
    @Mapping(target = "type", source = "typeId", qualifiedByName = "setTransactionType")
    @Mapping(target = "service", source = "serviceId", qualifiedByName = "setServices")
    Transaction toEntity(TransactionRequest request);
    TransactionResponse toResponse(Transaction source);
    TransactionTypeResponse toResponse(TransactionType source);
}
