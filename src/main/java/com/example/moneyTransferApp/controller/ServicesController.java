package com.example.moneyTransferApp.controller;

import com.example.moneyTransferApp.model.response.ProviderResponse;
import com.example.moneyTransferApp.model.response.ServicesResponce;
import com.example.moneyTransferApp.service.ServicesService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/service")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Slf4j
@RequiredArgsConstructor
public class ServicesController {
    ServicesService servicesService;

    @GetMapping("/providers")
    public Page<ProviderResponse> providers(
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        return  servicesService.allProviders(page, size);
    }

    @GetMapping("/services/{providerId}")
    public Page<ServicesResponce> providers(
            @PathVariable UUID providerId,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer size
    ){
        return servicesService.allServices(providerId,page, size);
    }

    @GetMapping("/list/providers")
    public List<ProviderResponse> providers(){
        return  servicesService.listProviders();
    }

    @GetMapping("/list/services/{providerId}")
    public List<ServicesResponce> providers(@PathVariable UUID providerId){
        return servicesService.listServices(providerId);
    }
}
