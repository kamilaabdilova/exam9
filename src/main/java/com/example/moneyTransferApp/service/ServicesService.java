package com.example.moneyTransferApp.service;

import com.example.moneyTransferApp.entity.Provider;
import com.example.moneyTransferApp.model.response.ProviderResponse;
import com.example.moneyTransferApp.model.response.ServicesResponce;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

public interface ServicesService {
    Page<ProviderResponse> allProviders(Integer page, Integer size);
    Page<ServicesResponce> allServices(UUID providerId, Integer page, Integer size);
    List<ProviderResponse> listProviders();
    List<ServicesResponce> listServices(UUID providerId);
}
