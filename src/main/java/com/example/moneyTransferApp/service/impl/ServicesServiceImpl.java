package com.example.moneyTransferApp.service.impl;

import com.example.moneyTransferApp.mapper.ServicesMapper;
import com.example.moneyTransferApp.model.response.ProviderResponse;
import com.example.moneyTransferApp.model.response.ServicesResponce;
import com.example.moneyTransferApp.repository.ProviderRepository;
import com.example.moneyTransferApp.repository.ServicesRepository;
import com.example.moneyTransferApp.service.ServicesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class ServicesServiceImpl implements ServicesService {
    ServicesRepository servicesRepository;
    ServicesMapper servicesMapper;
    ProviderRepository providerRepository;

    public Page<ProviderResponse> allProviders(Integer pageN, Integer sizeN){
        int page = pageN == null ? 0 : pageN;
        int size = sizeN == null ? 25 : sizeN;
        return providerRepository.findAll(PageRequest.of(page, size))
                .map(servicesMapper::toResponse);
    }
    public Page<ServicesResponce> allServices(UUID providerId, Integer pageN, Integer sizeN){
        int page = pageN == null ? 0 : pageN;
        int size = sizeN == null ? 100 : sizeN;
        return servicesRepository.findByProviderId(providerId, PageRequest.of(page, size))
                .map(servicesMapper::toResponse);
    }

    public List<ProviderResponse> listProviders(){
        return providerRepository.findAll().stream().map(servicesMapper::toResponse).collect(Collectors.toList());
    }

    public List<ServicesResponce> listServices(UUID providerId){
        return servicesRepository.findByProviderId(providerId).stream().map(servicesMapper::toResponse).collect(Collectors.toList());
    }
}
