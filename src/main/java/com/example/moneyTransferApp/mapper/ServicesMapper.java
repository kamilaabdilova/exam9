package com.example.moneyTransferApp.mapper;

import com.example.moneyTransferApp.entity.Provider;
import com.example.moneyTransferApp.entity.Services;
import com.example.moneyTransferApp.entity.Transaction;
import com.example.moneyTransferApp.entity.TransactionType;
import com.example.moneyTransferApp.model.response.ProviderResponse;
import com.example.moneyTransferApp.model.response.ServicesResponce;
import com.example.moneyTransferApp.model.response.TransactionResponse;
import com.example.moneyTransferApp.model.response.TransactionTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(
        componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        uses = {
                DefaultMapper.class
        }
)
public interface ServicesMapper {
        ServicesResponce toResponse(Services source);
        ProviderResponse toResponse(Provider source);
}
